package com.example.face.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
public class CheckInDetailVO {
    String className;
    String id;
    String classId;
    List<StudentVO> studentVOList;

}

