package com.example.face.controller;

import com.example.face.dao.CheckinDao;
import com.example.face.dao.ClassroomDao;
import com.example.face.dao.StudentDao;
import com.example.face.dao.StudentcheckinDao;
import com.example.face.domain.dto.CheckInDetailVO;
import com.example.face.domain.dto.CheckInItemVO;
import com.example.face.domain.dto.StudentVO;
import com.example.face.entity.Checkin;
import com.example.face.entity.Classroom;
import com.example.face.entity.Student;
import com.example.face.entity.Studentcheckin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@RestController
@RequestMapping("/checkIn")
public class CheckInController {

    @Resource
    private StudentcheckinDao studentcheckinDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private CheckinDao checkinDao;

    @Resource
    private ClassroomDao classroomDao;

    /**
     * 手动更新学生状态
     * @param studentId
     * @param status
     * @param checkInId
     * @return
     */
    @PostMapping("/updateStudentStatus")
    public ResponseEntity updateStudentStatus(@RequestParam("studentId") int studentId,
                                              @RequestParam("status") int status,
                                              @RequestParam("checkInId") int checkInId) {
        studentcheckinDao.updateStatus(studentId, checkInId, status);

        return ResponseEntity.ok("更新成功");
    }


    /**
     * 删除某个学生此次打卡记录
     * @param studentId
     * @param checkInId
     * @return
     */
    @PostMapping("/deleteStudentByCheckIn")
    public ResponseEntity deleteStudentByCheckIn(@RequestParam("studentId") int studentId,
                                              @RequestParam("checkInId") int checkInId) {
        studentcheckinDao.deleteStudentByCheckIn(studentId, checkInId);
        // TODO 其他表状态的变更

        return ResponseEntity.ok("删除学生成功");
    }

    /**
     * 获取打卡页面详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CheckInDetailVO getCheckInDetail(@PathVariable Integer id) {
        CheckInDetailVO checkInDetailVO = new CheckInDetailVO();

        List<Studentcheckin> studentcheckins = studentcheckinDao.queryByCheckInId(id);
        List<StudentVO> studentVOList = studentcheckins.stream()
                .map(studentcheckin -> {
                    StudentVO studentVO = new StudentVO();
                    Student student = studentDao.queryById(studentcheckin.getStudentId());
                    studentVO.setAvatar(student.getAvatar());
                    student.setName(student.getName());
                    studentVO.setStatus(studentcheckin.getStatus());
                    studentVO.setId(studentcheckin.getStudentId());
                    return studentVO;
                }).collect(Collectors.toList());

        checkInDetailVO.setStudentVOList(studentVOList);
        Classroom classroom = classroomDao.queryById(id);
        checkInDetailVO.setId(id);
        checkInDetailVO.setClassId(classroom.getId());
        checkInDetailVO.setClassName(classroom.getName());

        return checkInDetailVO;
    }

    /**
     * 首页 最近打卡列表
     * @param userId
     * @return
     */
    @GetMapping("/getCheckInList")
    public List<CheckInItemVO> getCheckInList(@RequestParam("userId") Integer userId) {
        List<CheckInItemVO> checkInItemVOS = new ArrayList<>();
        List<Checkin> checkins = checkinDao.queryAllByLimitOrderByAddTime(0, 20);
        checkInItemVOS = checkins.stream()
                .map(checkin -> {
                    List<Studentcheckin> studentcheckins = studentcheckinDao.queryByCheckInId(checkin.getId());
                    CheckInItemVO checkInItemVO = new CheckInItemVO();

                    Classroom classroom = classroomDao.queryById(checkin.getClassId());
                    checkInItemVO.setClassName(classroom.getName());
                    checkInItemVO.setRecentTime(checkin.getAddTime());
                    checkInItemVO.setStudentTotalNum(studentcheckins.size());
                    checkInItemVO.setStudentNum(studentcheckins.size());
                    return checkInItemVO;
                }).collect(Collectors.toList());

        return checkInItemVOS;
    }


}

