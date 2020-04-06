package com.example.face.http.request.ai.qq.response;

import lombok.Data;

/**
 * @author fan.li
 * @date 2020-04-05
 * @description
 */
@Data
public class AiQQFaceVerifyResponse {
    int ismatch;
    Double confidence;
}

