package com.example.face.http.request.ai.qq.response;

import lombok.Data;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
public class AiQQResponse<T> {
    int ret;
    String msg;
    T data;
}

