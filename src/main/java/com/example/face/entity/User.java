package com.example.face.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-03 16:55:08
 */
@Data
public class User {
    
    private int id;
    
    private String username;
    
    private String password;
    
    private Integer moblie;
    
    private Date addtime;
    
    private Date updatetime;
    
    private String email;
    
    private String nickname;
    
    private String avatar;
    /**
    * 用户类型
    */
    private Integer type;
    /**
    * 身份证号
    */
    private String idcard;

}