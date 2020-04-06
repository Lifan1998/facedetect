package com.example.face.http.request.ai.qq.request;

import lombok.Data;

/**
 * @author fan.li
 * @date 2020-04-05
 * @description
 */
@Data
public class AiQQFaceVerifyRequest extends AiQQRequest {
    String image;
    String person_id;
}

