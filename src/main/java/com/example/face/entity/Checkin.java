package com.example.face.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Checkin)实体类
 *
 * @author makejava
 * @since 2020-04-06 15:44:23
 */
public class Checkin implements Serializable {
    private static final long serialVersionUID = -43445743057924340L;
    
    private Integer id;
    
    private Integer classId;
    
    private Integer teacherId;
    
    private Date addTime;
    
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}