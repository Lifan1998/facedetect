package com.example.face.util;

import com.example.face.http.request.ai.qq.request.AiQQRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Slf4j
public class HttpUtils {
    public static RequestBody getOkHttpRequest(AiQQRequest aiQQRequest) {
        Map<String, Object> params = AiQQRequest.getPostParam(aiQQRequest);


        FormBody.Builder build = new FormBody.Builder();
        params.keySet().stream().forEach(key -> {
            build.add(key, params.get(key).toString());
        });

        return build.build();
    }
}

