package com.example.face.http.request.ai.qq.request;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

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
    String nonce_str = "20e3408379";
    String sign;

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

        params.keySet().stream().sorted().forEach(key -> {

            try {
                sign.append(key + "=" + URLEncoder.encode(params.get(key).toString(),"UTF-8") + "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        sign.append("app_key=" + app_key);


        /**
         * MD5加密+encode大写
         */
        params.put("sign", DigestUtils.md5DigestAsHex(sign.toString().getBytes()).toUpperCase());

        return params;
    }

}

