package com.example.face.http.request.ai.qq.request;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
@Slf4j
public class AiQQRequest {
    static String app_key = "ZaT5Y332RtWAEZBH";
    int app_id = 2131852408;
    int time_stamp;
    String nonce_str = "20e3408a79";
    String sign;
//    String key1 = "";
//    String key2 = "";

    /**
     * sign 加密
     * @param aiQQRequest
     * @return
     */
    public static Map<String, Object> getPostParam(AiQQRequest aiQQRequest) {
        aiQQRequest.setTime_stamp((int) (System.currentTimeMillis()/1000));
        JSONObject jsonObject = (JSONObject) JSON.toJSON(aiQQRequest);
        TreeMap<String, Object> params = (TreeMap<String, Object>) JSON.parseObject(jsonObject.toJSONString(), TreeMap.class);
        StringBuilder sign = new StringBuilder();
        log.info("sign handle aiqqrequest {}", params);

        params.keySet().stream().sorted().forEach(key -> {

            try {
                sign.append(key + "=" + URLEncoder.encode(params.get(key).toString(),"UTF-8") + "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            log.info("sign handle {}", sign.toString());
        });

        sign.append("app_key=" + app_key);

        log.info("sign handle {}", sign.toString());

        /**
         * MD5加密+encode大写
         */
        params.put("sign", DigestUtils.md5DigestAsHex(sign.toString().getBytes()).toUpperCase());

        return params;
    }

    public static void main(String[] args) {
        AiQQRequest aiQQRequest = new AiQQRequest();
        aiQQRequest.setTime_stamp(1493449657);
        aiQQRequest.setApp_id(10000);
        aiQQRequest.setNonce_str("20e3408a79");
//        aiQQRequest.setKey1("腾讯AI开放平台");
//        aiQQRequest.setKey2("示例仅供参考");
        AiQQRequest.app_key = "a95eceb1ac8c24ee28b70f7dbba912bf";

        Map<String, Object> params = getPostParam(aiQQRequest);
        System.out.println(params.get("sign"));
    }



}

