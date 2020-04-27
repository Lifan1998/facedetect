package com.example.face.service.impl;

import com.example.face.dao.*;
import com.example.face.enmu.StudentStatus;
import com.example.face.entity.*;
import com.example.face.http.request.FaceDetectMultifaceRequest;
import com.example.face.http.request.ai.qq.AlQQApiService;
import com.example.face.http.request.ai.qq.TencentCouldFaceService;
import com.example.face.http.request.ai.qq.request.AiQQDetectmultifaceRequest;
import com.example.face.http.request.ai.qq.request.AiQQFaceVerifyRequest;
import com.example.face.http.request.ai.qq.request.AiQQNewPersonRequest;
import com.example.face.http.request.ai.qq.response.AiQQDetectmultifaceResponse;
import com.example.face.http.request.ai.qq.response.AiQQFaceVerifyResponse;
import com.example.face.http.request.ai.qq.response.AiQQNewPersonResponse;
import com.example.face.http.request.ai.qq.response.AiQQResponse;
import com.example.face.service.CheckInService;
import com.example.face.service.FaceDetectService;
import com.example.face.service.StudentcheckinService;
import com.example.face.util.ImageUtils;
import com.tencentcloudapi.iai.v20180301.models.CreatePersonResponse;
import com.tencentcloudapi.iai.v20180301.models.VerifyPersonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-04-06
 * @description
 */
@Service
@Slf4j
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    AlQQApiService  alQQApiService;

    @Autowired
    TencentCouldFaceService tencentCouldFaceService;

    @Autowired
    private FaceDetectService faceDetectService;

    @Autowired
    private StudentcheckinService studentcheckinService;

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

    @Resource
    private CheckinDao checkinDao;
    /**
     * 新建一个打卡记录
     *
     * @param faceDetectRequest
     * @return
     */
    @Override
    public ResponseEntity createCheckIn(FaceDetectMultifaceRequest faceDetectRequest) {

        // TODO 来看看这个班级是不是已经有了，没有的话要新建, 如果这张图片都是新人，则判断是新的班级

        if (faceDetectRequest.getClassroomId() == null || faceDetectRequest.getClassroomId() == 0) {
            processClassroomId(faceDetectRequest);
        }

        Checkin checkin = new Checkin();
        checkin.setClassId(faceDetectRequest.getClassroomId());
        checkin.setTeacherId(faceDetectRequest.getUserId());
        checkinDao.insert(checkin);
        faceDetectRequest.setCheckInId(checkin.getId());


        updateCheckIn(faceDetectRequest);

        return ResponseEntity.ok("创建打卡成功");
    }

    /**
     * 更新打卡记录
     *
     * @param faceDetectRequest
     * @return
     */
    @Override
    public ResponseEntity updateCheckIn(FaceDetectMultifaceRequest faceDetectRequest) {

        // 图片加工
        List<String> base64ImageList = faceDetectService.getBase64ImageStringList(faceDetectRequest.getImage());

        log.info("base64ImageList size: {}", base64ImageList.size());

        if (faceDetectRequest.getClassroomId() == null) {
            Checkin checkin = checkinDao.queryById(faceDetectRequest.getCheckInId());
            faceDetectRequest.setClassroomId(checkin.getClassId());
        }

        // 1. 找到打卡组(班级)
        List<Integer> studentIds = studentclassDao.queryByClassId(faceDetectRequest.getClassroomId())
                .stream()
                .map(studentclass -> studentclass.getStudentId())
                .collect(Collectors.toList());

        log.info("studentIds: {}", studentIds);

        List<String> base64ImageListCopy = new ArrayList<>(base64ImageList);

        base64ImageList.forEach(base64Image -> {
            studentIds.forEach(studentId -> {
                // 判断图片与人是否匹配
//                AiQQFaceVerifyRequest aiQQFaceVerifyRequest = new AiQQFaceVerifyRequest();
//                aiQQFaceVerifyRequest.setImage(base64Image);
//                aiQQFaceVerifyRequest.setPerson_id(studentId + "");
//
//                AiQQResponse<AiQQFaceVerifyResponse> aiQQResponse = alQQApiService.faceVerify(aiQQFaceVerifyRequest);

                VerifyPersonResponse verifyPersonResponse = tencentCouldFaceService.verifyPerson(studentId + "", base64Image);

                if (verifyPersonResponse.getIsMatch()) {
                    // 移除处理掉的图片
                    base64ImageListCopy.remove(base64Image);

                    Checkin checkin = checkinDao.queryById(faceDetectRequest.getCheckInId());
                    long nowTime = checkin.getAddTime().getTime();
                    // 打卡记录创建半小时后的时间
                    long normalTime = nowTime + 1000 * 60 * 30;
                    if (System.currentTimeMillis() > normalTime) {
                        // 迟到了
                        studentcheckinService.createOrUpdateStatus(studentId, faceDetectRequest.getCheckInId(), StudentStatus.BE_LATE.getValue());
                    } else {
                        // 正常签到
                        studentcheckinService.createOrUpdateStatus(studentId, faceDetectRequest.getCheckInId(), StudentStatus.NORMAL.getValue());
                    }
                }
            });
        });

        // 来处理刚才未识别的新的图片
        base64ImageListCopy.stream().forEach(base64Image -> {
            // 新建学生
            // 新建人脸信息
            Student student = new Student();
            student.setName("学生");
            File file = ImageUtils.base64ToFile(base64Image);
            student.setAvatar("http://106.14.141.90/face/" + file.getName());
            student.setStudentNo("12345678");
            studentDao.insert(student);
            student.setName(student.getName() + student.getId());
            studentDao.update(student);

            Studentclass studentclass = new Studentclass();
            studentclass.setClassId(faceDetectRequest.getClassroomId());
            studentclass.setStudentId(student.getId());
            studentclassDao.insert(studentclass);

//            AiQQNewPersonRequest aiQQNewPersonRequest = new AiQQNewPersonRequest();
//            aiQQNewPersonRequest.setGroup_ids(faceDetectRequest.getClassroomId()+"");
//            aiQQNewPersonRequest.setImage(base64Image);
//            aiQQNewPersonRequest.setPerson_id(student.getId() + "");
//            aiQQNewPersonRequest.setPerson_name(student.getName());
//            aiQQNewPersonRequest.setTag(student.getName());
//            AiQQResponse<AiQQNewPersonResponse> newPersonResponse = alQQApiService.newPerson(aiQQNewPersonRequest);

            CreatePersonResponse createPersonResponse = tencentCouldFaceService.createPerson(faceDetectRequest.getClassroomId() + "", student.getId() + "", base64Image, student.getName());
            if (createPersonResponse == null) {
                // 新建失败了
                // 手动回滚删除学生
                studentDao.deleteById(student.getId());
                studentclassDao.deleteById(studentclass.getId());
                throw new RuntimeException( "人脸入库失败, 腾讯云拒绝此次服务，请检查接口调用次数");
            } else {
                // 添加打卡记录
                // TODO 迟到
                studentcheckinService.createOrUpdateStatus(student.getId(), faceDetectRequest.getCheckInId(), StudentStatus.NORMAL.getValue());
            }

        });

        return ResponseEntity.ok("识别成功");
    }


    private void processClassroomId(FaceDetectMultifaceRequest faceDetectRequest) {

        // 图片加工
        List<String> base64ImageList = faceDetectService.getBase64ImageStringList(faceDetectRequest.getImage());

        List<Integer> classIds = teacherclassDao.queryByTeacherId(faceDetectRequest.getUserId())
                .stream().map(teacherclass -> teacherclass.getClassId()).collect(Collectors.toList());
        Map<Integer, Integer> studentIdsAndClassId = new HashMap<>();

        classIds.forEach(integer -> {
            studentclassDao.queryByClassId(integer).stream()
                    .forEach(studentclass -> {
                        studentIdsAndClassId.put(studentclass.getStudentId(), studentclass.getClassId());
                    });

        });

        log.info("studentIdsAndClassId {}", studentIdsAndClassId);

        base64ImageList.forEach(base64Image -> {
//            AiQQFaceVerifyRequest aiQQFaceVerifyRequest = new AiQQFaceVerifyRequest();
//
//            aiQQFaceVerifyRequest.setImage(base64Image);

            // 找所有的学生
            studentIdsAndClassId.keySet().stream().forEach(studentId -> {
//                aiQQFaceVerifyRequest.setPerson_id(studentId + "");
                VerifyPersonResponse verifyPersonResponse = tencentCouldFaceService.verifyPerson(studentId + "", base64Image);

//                AiQQResponse<AiQQFaceVerifyResponse> aiQQResponse = alQQApiService.faceVerify(aiQQFaceVerifyRequest);

                if (verifyPersonResponse.getIsMatch()) {
                    // 找到了设置班级
                    faceDetectRequest.setClassroomId(studentIdsAndClassId.get(studentId));
                }
            });
        });

        // 找了一圈没找到，说明照片里全是新人
        if (faceDetectRequest.getClassroomId() == null || faceDetectRequest.getClassroomId() == 0) {
            // 新建班级并建立人脸识别组
            Classroom classroom = new Classroom();
            SimpleDateFormat format = new SimpleDateFormat("MMdd");
            classroom.setName("班级" + format.format(new Date()));
            classroomDao.insert(classroom);
            // 新建打卡组
            tencentCouldFaceService.createGroup(classroom.getId() + "", classroom.getName(), classroom.getName());
            Teacherclass teacherclass = new Teacherclass();
            teacherclass.setClassId(classroom.getId());
            teacherclass.setTeacherId(faceDetectRequest.getUserId());
            teacherclassDao.insert(teacherclass);
            faceDetectRequest.setClassroomId(classroom.getId());
        }
    }


}

