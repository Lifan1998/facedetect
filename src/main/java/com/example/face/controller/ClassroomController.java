package com.example.face.controller;

import com.example.face.dao.*;
import com.example.face.domain.dto.ClassRoomItemVO;
import com.example.face.domain.dto.StudentCountVO;
import com.example.face.domain.dto.StudentVO;
import com.example.face.enmu.StudentStatus;
import com.example.face.entity.*;
import com.example.face.service.ClassroomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Classroom)表控制层
 *
 * @author makejava
 * @since 2020-03-26 21:45:38
 */
@RestController
@RequestMapping("classroom")
public class ClassroomController {
    /**
     * 服务对象
     */
    @Resource
    private ClassroomService classroomService;

    @Resource
    private TeacherclassDao teacherclassDao;

    @Resource
    private CheckinDao checkinDao;

    @Resource
    private StudentcheckinDao studentcheckinDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private ClassroomDao classroomDao;


    /**
     * 获取统计界面的学生列表
     * @param classroomId
     * @param userId = teacherId
     * @param fromTime
     * @param toTime
     * @return
     */
    @GetMapping("/{classroomId}")
    public List<StudentCountVO> get(@PathVariable("classroomId") int classroomId, @RequestParam("userId") int userId,
                                    @RequestParam("fromTime") Date fromTime,
                                    @RequestParam("toTime") Date toTime) {

        List<Studentcheckin> studentcheckinList = new ArrayList<>();
        // 拿到该这个班级下的所有打卡记录
        List<Checkin> checkinList = checkinDao.queryByTeacherIdAndClassId(userId, classroomId);

        checkinList.forEach(checkin -> {
            List<Studentcheckin> studentcheckins = studentcheckinDao.queryByCheckInId(checkin.getId());
            studentcheckinList.addAll(studentcheckins);
        });

        Map<Integer, List<Studentcheckin>> integerListMap = studentcheckinList.stream()
                .collect(Collectors.groupingBy(Studentcheckin::getStudentId));

        List<StudentCountVO> studentCountVOList = integerListMap.keySet()
                .stream()
                .map(studentId -> {
                    Student student = studentDao.queryById(studentId);
                    StudentCountVO studentCountVO = new StudentCountVO();
                    studentCountVO.setId(studentId);
                    studentCountVO.setName(student.getName());
                    studentCountVO.setStudentNo(student.getStudentNo());
                    List<Studentcheckin> studentcheckins = integerListMap.get(studentId);
                    studentCountVO.setNormal(studentcheckins.stream().
                            filter(studentcheckin -> studentcheckin.getStatus() == StudentStatus.NORMAL.getValue())
                            .collect(Collectors.toList()).size());

                    studentCountVO.setBeLate(studentcheckins.stream().
                            filter(studentcheckin -> studentcheckin.getStatus() == StudentStatus.BE_LATE.getValue())
                            .collect(Collectors.toList()).size());

                    studentCountVO.setAbsence(studentcheckins.stream().
                            filter(studentcheckin -> studentcheckin.getStatus() == StudentStatus.ABSENCE.getValue())
                            .collect(Collectors.toList()).size());

                    studentCountVO.setLeave(studentcheckins.stream().
                            filter(studentcheckin -> studentcheckin.getStatus() == StudentStatus.LEAVE.getValue())
                            .collect(Collectors.toList()).size());

                    return studentCountVO;

                }).collect(Collectors.toList());


        return studentCountVOList;
    }


    /**
     * 获取统计页面的班级列表
     * @param userId
     * @return
     */
    @GetMapping("/list")
    public List<ClassRoomItemVO> getClassroomList(@RequestParam("userId") int userId) {
        // 拿到这个老师的所有班级
        List<Teacherclass> teacherclasses = teacherclassDao.queryByTeacherId(userId);
        List<ClassRoomItemVO> classRoomItemVOS = teacherclasses.stream()
                .map(teacherclass -> {
                    ClassRoomItemVO classRoomItemVO = new ClassRoomItemVO();
                    classRoomItemVO.setClassId(teacherclass.getClassId());
                    List<Checkin> checkinList = checkinDao.queryByTeacherIdAndClassId(userId, teacherclass.getClassId());
                    Classroom classroom = classroomDao.queryById(teacherclass.getClassId());
                    classRoomItemVO.setName(classroom.getName());

                    List<Studentcheckin> studentcheckinList = new ArrayList<>();
                    // 拿到该这个班级下的所有打卡记录

                    checkinList.forEach(checkin -> {
                        List<Studentcheckin> studentcheckins = studentcheckinDao.queryByCheckInId(checkin.getId());
                        studentcheckinList.addAll(studentcheckins);
                    });
                    // 分组
                    Map<Integer, List<Studentcheckin>> integerListMap = studentcheckinList.stream()
                            .collect(Collectors.groupingBy(Studentcheckin::getStudentId));

                    // 拿第一个一个打卡记录做更新时间
                    Checkin checkin = checkinList.stream().findFirst().orElse(new Checkin());

                    classRoomItemVO.setUpdateTime(checkin.getUpdateTime());


                    classRoomItemVO.setTotalNum(integerListMap.size());
                    return classRoomItemVO;

                }).collect(Collectors.toList());
        return classRoomItemVOS;
    }


}