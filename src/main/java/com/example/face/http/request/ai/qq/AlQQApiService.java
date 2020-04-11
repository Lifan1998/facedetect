package com.example.face.http.request.ai.qq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.face.http.request.ai.qq.request.AiQQGetPersonIdsRequest;
import com.example.face.http.request.ai.qq.request.AiQQRequest;
import com.example.face.http.request.ai.qq.response.AiQQDetectmultifaceResponse;
import com.example.face.http.request.ai.qq.response.AiQQFaceVerifyResponse;
import com.example.face.http.request.ai.qq.response.AiQQNewPersonResponse;
import com.example.face.http.request.ai.qq.response.AiQQResponse;
import com.example.face.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Service
@Slf4j
public class AlQQApiService {

    /**
     * 多人脸识别
     * @param aiQQRequest
     * @return
     */
    public AiQQResponse<AiQQDetectmultifaceResponse> faceDetectmultiface(AiQQRequest aiQQRequest) {


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_detectmultiface")
                .post(requestBody)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            log.info("info {}", "onResponse: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);

        AiQQResponse<AiQQDetectmultifaceResponse> aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQDetectmultifaceResponse>>(){});
        log.info("aiQQresponse: {}", aiQQResponse);
        return aiQQResponse;
    }

    /**
     * 人脸验证
     * @param aiQQRequest
     * @return
     */
    public AiQQResponse<AiQQFaceVerifyResponse> faceVerify(AiQQRequest aiQQRequest) {
        AiQQResponse<AiQQFaceVerifyResponse> aiQQResponse;


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_faceverify")
                .post(requestBody)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            log.info("info {}", "onResponse: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQFaceVerifyResponse>>(){});
        return aiQQResponse;
    }

    /**
     * 创建个体
     * @param aiQQRequest
     * @return
     */
    public AiQQResponse<AiQQNewPersonResponse> newPerson(AiQQRequest aiQQRequest) {
        AiQQResponse<AiQQNewPersonResponse> aiQQResponse;


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_newperson")
                .post(requestBody)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            log.info("info {}", "onResponse: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQNewPersonResponse>>(){});
        return aiQQResponse;
    }

    public AiQQResponse<AiQQGetPersonIdsRequest> getPersonIds(AiQQRequest aiQQRequest) {
        AiQQResponse<AiQQGetPersonIdsRequest> aiQQResponse;


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_getpersonids")
                .post(requestBody)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            log.info("info {}", "onResponse: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);

        aiQQResponse = (AiQQResponse<AiQQGetPersonIdsRequest>) JSON.parse(response.body().toString());
        return aiQQResponse;
    }


}

