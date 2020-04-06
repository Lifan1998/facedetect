package com.example.face.controller;

import com.example.face.domain.dto.StudentCountVO;
import com.example.face.entity.Classroom;
import com.example.face.service.ClassroomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

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


    /**
     * 获取统计界面的学生列表
     * @param classroomId
     * @param userId
     * @param fromTime
     * @param toTime
     * @return
     */
    @GetMapping("/{classroomId}")
    public List<StudentCountVO> get(@PathVariable("classroomId") int classroomId, @RequestParam("userId") int userId,
                                    @RequestParam("fromTime") Date fromTime,
                                    @RequestParam("toTime") Date toTime) {
        return null;
    }

    /**
     * 获取统计页面的班级列表
     * @param userId
     * @return
     */
    @GetMapping("/list")
    public List<Classroom> getClassroomList(@RequestParam("userId") int userId) {

        return null;
    }


}