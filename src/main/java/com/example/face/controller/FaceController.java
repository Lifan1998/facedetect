package com.example.face.controller;

import com.example.face.dao.*;
import com.example.face.enmu.StudentStatus;
import com.example.face.entity.*;
import com.example.face.http.request.FaceDetectMultifaceRequest;
import com.example.face.http.request.ai.qq.QQAlApiService;
import com.example.face.http.request.ai.qq.request.AiQQDetectmultifaceRequest;
import com.example.face.http.request.ai.qq.request.AiQQFaceVerifyRequest;
import com.example.face.http.request.ai.qq.request.AiQQNewPersonRequest;
import com.example.face.http.request.ai.qq.response.AiQQDetectmultifaceResponse;
import com.example.face.http.request.ai.qq.response.AiQQFaceVerifyResponse;
import com.example.face.http.request.ai.qq.response.AiQQNewPersonResponse;
import com.example.face.http.request.ai.qq.response.AiQQResponse;
import com.example.face.util.ImageUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@RestController
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private QQAlApiService qqAlApiService;
    @Resource
    private StudentclassDao studentclassDao;
    @Resource
    private TeacherclassDao teacherclassDao;
    @Resource
    private StudentcheckinDao studentcheckinDao;
    @Resource
    private ClassroomDao classroomDao;

    @Resource
    private StudentDao studentDao;

    /**
     * 图片识别
     * 1. 创建班级
     * 2. 创建打卡记录
     * 3. 修改打卡记录
     * @param faceDetectRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<String> faceDetectmultiface(@RequestBody FaceDetectMultifaceRequest faceDetectRequest) {
        // 1. 多人脸处理
        AiQQDetectmultifaceRequest request = new AiQQDetectmultifaceRequest();
        request.setImage(faceDetectRequest.getImage());
        request.setTime_stamp((int) (System.currentTimeMillis()/1000));
        AiQQResponse<AiQQDetectmultifaceResponse> response = qqAlApiService.faceDetectmultiface(request);


        // 判断有没有人脸
        if (!response.getData().getFace_list().isEmpty()) {
            // 记录找到的学生
            List<Integer> findStudentIds = new ArrayList<>();

            // 记录新建的学生
            List<Integer> notFoundStudentIds = new ArrayList<>();

            response.getData().getFace_list().forEach(faceLocation -> {
                // 待处理的人脸
                String base64Image;
                // 判断是不是单人图片
                if (response.getData().getFace_list().size() != 1) {
                    File file = ImageUtils.base64ToFile(faceDetectRequest.getImage());
                    List<File> fileList =  new ArrayList<>();
                    try {
                        fileList =  Thumbnails.of(file)
                                .sourceRegion(faceLocation.getX1().intValue(), faceLocation.getY1().intValue(), faceLocation.getX2().intValue(), faceLocation.getY2().intValue())
                                .outputFormat("jpg").asFiles(Rename.PREFIX_DOT_THUMBNAIL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 读取文件转base64
                    // TODO 图片选组
                    File targetFile = fileList.stream().findFirst().get();
                    base64Image = ImageUtils.encodeFileToBase64Binary(targetFile);
                } else {
                    // 图片只有一个人，不用裁剪了
                    base64Image = faceDetectRequest.getImage();
                }

                // 1. 新建打卡
                if (faceDetectRequest.isCreateCheckIn()) {
                    // 找出这个人，拿到班级
                    // 拿到这个老师所有学生人脸信息，老师 -> 班级 -> 学生
                    // TODO 所有图片都要遍历，这里后面抽成service
                    List<Integer> classIds = teacherclassDao.queryByTeacherId(faceDetectRequest.getUserId())
                            .stream().map(teacherclass -> teacherclass.getClassId()).collect(Collectors.toList());
                    Map<Integer, Integer> studentIdsAndClassId = new HashMap<>();

                    classIds.forEach(integer -> {
                        studentclassDao.queryByClassId(integer).stream()
                                .forEach(studentclass -> {
                                    studentIdsAndClassId.put(studentclass.getClassId(), studentclass.getClassId());
                                });

                    });

                    AiQQFaceVerifyRequest aiQQFaceVerifyRequest = new AiQQFaceVerifyRequest();

                    aiQQFaceVerifyRequest.setImage(base64Image);

                    studentIdsAndClassId.keySet().stream().forEach(studentId -> {
                        aiQQFaceVerifyRequest.setPerson_id(studentId + "");

                        AiQQResponse<AiQQFaceVerifyResponse> aiQQResponse = qqAlApiService.faceVerify(aiQQFaceVerifyRequest);

                        if (aiQQResponse.getData().getIsmatch() == 1) {
                            // 设置班级
                            faceDetectRequest.setClassroomId(studentIdsAndClassId.get(studentId));
                        }

                    });

                    // 空的话说明这个人是新人
                    if (faceDetectRequest.getClassroomId() == null || faceDetectRequest.getClassroomId() == 0) {
                        // 新建班级并建立人脸识别组
                        Classroom classroom = new Classroom();
                        classroom.setName("新建班级(" + "2020-03-03" + ")");
                        classroomDao.insert(classroom);
                        Teacherclass teacherclass = new Teacherclass();
                        teacherclass.setClassId(classroom.getId());
                        teacherclass.setTeacherId(faceDetectRequest.getUserId());
                        teacherclassDao.insert(teacherclass);

                        // 新建学员并建立人脸识别个体
                        // TODO 学生群组
                        Student student = new Student();
                        student.setName("学生1");
                        student.setStudentNo("未知");
                        studentDao.insert(student);
                        Studentclass studentclass = new Studentclass();
                        studentclass.setClassId(classroom.getId());
                        studentclass.setStudentId(student.getId());
                        studentclassDao.insert(studentclass);

                        AiQQNewPersonRequest aiQQNewPersonRequest = new AiQQNewPersonRequest();
                        aiQQNewPersonRequest.setGroup_ids(classroom.getId() + "");
                        aiQQNewPersonRequest.setImage(base64Image);
                        aiQQNewPersonRequest.setPerson_id(student.getId() + "");
                        aiQQNewPersonRequest.setPerson_name(student.getName());
                        AiQQResponse<AiQQNewPersonResponse> newPersonResponse = qqAlApiService.newPerson(aiQQFaceVerifyRequest);
                        if (newPersonResponse.getRet() == 0) {
                            // 识别失败了
                            // 手动回滚删除学生
                            studentDao.deleteById(student.getId());
                            studentclassDao.deleteById(studentclass.getId());
                        }
                    } else {
                        // 找的到拿班级，按更新打卡处理
                    }

                } else {
                    // 更新打卡
                    // 1. 找到打卡组(班级)
                    List<Integer> studentIds = studentclassDao.queryByClassId(faceDetectRequest.getClassroomId())
                            .stream()
                            .map(studentclass -> studentclass.getStudentId())
                            .collect(Collectors.toList());
                    studentIds.forEach(studentId -> {
                        AiQQFaceVerifyRequest aiQQFaceVerifyRequest = new AiQQFaceVerifyRequest();

                        aiQQFaceVerifyRequest.setImage(base64Image);
                        aiQQFaceVerifyRequest.setPerson_id(studentId + "");

                        AiQQResponse<AiQQFaceVerifyResponse> aiQQResponse = qqAlApiService.faceVerify(aiQQFaceVerifyRequest);

                        if (aiQQResponse.getData().getIsmatch() == 1) {
                            // 设置状态
                            Studentcheckin studentcheckin = studentcheckinDao.queryById(faceDetectRequest.getCheckInId());
                            long nowTime = studentcheckin.getAddtime().getTime();
                            // 打卡记录创建半小时后的时间
                            long normalTime = nowTime + 1000 * 60 * 30;
                            if (System.currentTimeMillis() > normalTime) {
                                // 迟到了
                                studentcheckinDao.updateStatus(faceDetectRequest.getCheckInId(), StudentStatus.BE_LATE.getValue());
                            } else {
                                // 正常签到
                                studentcheckinDao.updateStatus(faceDetectRequest.getCheckInId(), StudentStatus.NORMAL.getValue());
                            }
                        }
                    });
                }

            });

        } else {
            // 人脸为空返回提示信息
            new RuntimeException("图片无有效人脸，请重新拍照");
        }

        return ResponseEntity.ok("识别成功");
    }

}

