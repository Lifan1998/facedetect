package com.example.face.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (Bookmark)实体类
 *
 * @author makejava
 * @since 2020-05-07 19:53:19
 */
@Data
public class Bookmark implements Serializable {
    private static final long serialVersionUID = 661093571585260638L;
    
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 添加时间
    */
    private Date addTime;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 链接
    */
    private String url;
    /**
    * 链接描述
    */
    private String name;
    /**
    * 链接类型：0 历史记录 1 书签
    */
    private Long urlType;


}