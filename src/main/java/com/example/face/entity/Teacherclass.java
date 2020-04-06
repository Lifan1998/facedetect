package com.example.face.entity;

import java.io.Serializable;

/**
 * (Teacherclass)实体类
 *
 * @author makejava
 * @since 2020-04-05 21:33:57
 */
public class Teacherclass implements Serializable {
    private static final long serialVersionUID = 130149015205106839L;
    
    private Integer id;
    
    private Integer teacherId;
    
    private Integer classId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

}