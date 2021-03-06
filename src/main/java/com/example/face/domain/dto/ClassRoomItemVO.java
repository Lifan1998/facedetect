package com.example.face.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author fan.li
 * @date 2020-04-06
 * @description
 */
@Data
public class ClassRoomItemVO {
    int classId;
    String name;
    int totalNum;
    Date updateTime;
}

