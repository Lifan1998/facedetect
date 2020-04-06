package com.example.face.controller;

import com.example.face.enmu.LoginType;
import com.example.face.entity.User;
import com.example.face.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-30 20:51:27
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{userId}")
    public User user(@PathVariable("userId") int id) {
        return userService.queryById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String account,
                                        @RequestParam String password,
                                        @RequestParam Integer type) {
        return userService.login(type, account, password);
    }

}