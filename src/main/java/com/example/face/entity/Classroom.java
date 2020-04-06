package com.example.face.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Classroom)实体类
 *
 * @author makejava
 * @since 2020-04-06 13:28:23
 */
public class Classroom implements Serializable {
    private static final long serialVersionUID = -99991986877181296L;
    
    private Integer id;
    
    private String name;
    
    private Date addtime;
    
    private Date updatetime;


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