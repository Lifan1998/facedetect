package com.example.face.entity;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-04-06 13:33:58
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -53980545806915058L;
    
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 学号
    */
    private String studentNo;
    /**
    * 用户id
    */
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}