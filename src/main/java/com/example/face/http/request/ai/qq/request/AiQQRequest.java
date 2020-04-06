package com.example.face.http.request.ai.qq.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
@Builder
public class AiQQRequest {
    final static String app_key = "ZaT5Y332RtWAEZBH";
    @Builder.Default
    int app_id = 2131852408;
    int time_stamp;
    @Builder.Default
    String nonce_str = "20e3408a79";
    String sign;

    /**
     * sign 加密
     * @param aiQQRequest
     * @return
     */
    public static Map<String, String> getPostParam(AiQQRequest aiQQRequest) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(aiQQRequest);
        Map<String, String> params = (Map<String, String>) JSON.parse(jsonObject.toJSONString());
        StringBuilder sign = new StringBuilder();

        params.keySet().stream().forEach(key -> {
            sign.append(key + "=" + URLEncoder.encode(params.get(key)) + "&");
        });

        sign.append("app_key" + app_key);

        /**
         * MD5加密+encode大写
         */
        params.put("sign", DigestUtils.md5DigestAsHex(sign.toString().getBytes()).toUpperCase());

        return params;
    }



}

