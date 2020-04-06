package com.example.face.domain.dto;

import com.example.face.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author fan.li
 * @date 2020-04-03
 * @description
 */
@Data
public class StudentCountVO {

    String id;
    String name;
    /**
     * 学号
     */
    String studentNo;
    /**
     * 正常打卡次数
     */
    int normal;
    int beLate;
    int absence;
    int leave;
}

