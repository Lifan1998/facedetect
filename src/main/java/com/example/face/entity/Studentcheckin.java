package com.example.face.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Studentcheckin)实体类
 *
 * @author makejava
 * @since 2020-04-06 15:07:40
 */
public class Studentcheckin implements Serializable {
    private static final long serialVersionUID = 929289291911913172L;
    
    private Integer id;
    
    private Integer studentId;
    
    private Integer checkinId;
    
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

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
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