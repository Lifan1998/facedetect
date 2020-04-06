package com.example.face.entity;

import java.io.Serializable;

/**
 * (Studentclass)实体类
 *
 * @author makejava
 * @since 2020-04-05 21:37:31
 */
public class Studentclass implements Serializable {
    private static final long serialVersionUID = 675141961182656393L;
    
    private Integer id;
    
    private Integer studentId;
    
    private Integer classId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

}