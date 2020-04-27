package com.example.face.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Harmurl)实体类
 *
 * @author makejava
 * @since 2020-04-27 22:00:46
 */
@Data
public class Harmurl implements Serializable {
    private static final long serialVersionUID = 951710946529559135L;
    
    private Integer id;
    /**
    * 恶意网址
    */
    private String url;
    /**
    * 恶意网址域名
    */
    private String domain;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 添加时间
    */
    private Date addTime;


}