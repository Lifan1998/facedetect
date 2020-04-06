package com.example.face.service;

import java.util.List;

/**
 * @author fan.li
 * @date 2020-04-06
 * @description
 */

public interface FaceDetectService {


    /**
     * 获取人脸组
     * @param base64Image
     * @return
     */
    List<String> getBase64ImageStringList(String base64Image);
}

