package com.example.face.controller;

import com.example.face.dao.StudentcheckinDao;
import com.example.face.domain.dto.CheckInDetailVO;
import com.example.face.domain.dto.CheckInItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@RestController
@RequestMapping("/checkIn")
public class CheckInController {

    @Autowired
    private StudentcheckinDao studentcheckinDao;

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
        return studentcheckinDao.updateStatus(checkInId, s);
    }


    /**
     * 删除某个学生此次打卡记录
     * @param userId
     * @param checkInId
     * @return
     */
    @PostMapping("/deleteStudentByCheckIn")
    public ResponseEntity deleteStudentByCheckIn(@RequestParam("userId") int userId,
                                              @RequestParam("checkInId") int checkInId) {
        return null;
    }

    /**
     * 获取打卡页面详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CheckInDetailVO getCheckInDetail(@PathVariable Integer id) {
        CheckInDetailVO checkInDetailVO = new CheckInDetailVO();
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
        return checkInItemVOS;
    }


}

