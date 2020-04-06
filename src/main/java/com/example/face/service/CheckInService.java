package com.example.face.service;

import com.example.face.http.request.FaceDetectMultifaceRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author fan.li
 * @date 2020-04-06
 * @description
 */

public interface CheckInService {
    /**
     * 新建一个打卡记录
     * @param detectMultifaceRequest
     * @return
     */
    ResponseEntity createCheckIn(FaceDetectMultifaceRequest detectMultifaceRequest);

    /**
     * 更新打卡记录
     * @param detectMultifaceRequest
     * @return
     */
    ResponseEntity updateCheckIn(FaceDetectMultifaceRequest detectMultifaceRequest);

}

