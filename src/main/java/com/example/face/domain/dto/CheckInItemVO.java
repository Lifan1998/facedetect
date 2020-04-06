package com.example.face.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
public class CheckInItemVO {
    String className;
    Date recentTime;
    /**
     * 总人数
     */
    int studentTotalNum;
    /**
     * 识别人数
     */
    int studentNum;
}

