package com.example.face.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Studentcheckin)实体类
 *
 * @author makejava
 * @since 2020-04-05 22:06:54
 */
public class Studentcheckin implements Serializable {
    private static final long serialVersionUID = 842230026456664455L;
    
    private Integer id;
    
    private Integer studentId;
    
    private Integer classId;
    
    private Integer teacherId;
    
    private Integer status;
    
    private Date addtime;
    
    private Date updatetime;


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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}