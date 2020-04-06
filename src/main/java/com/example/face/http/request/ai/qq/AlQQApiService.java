package com.example.face.http.request.ai.qq;

import com.alibaba.fastjson.JSON;
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
import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

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

        AiQQResponse<AiQQDetectmultifaceResponse> aiQQResponse;


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_detectmultiface")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("" + TAG, "onResponse: " + response.body().string());
            
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        aiQQResponse = (AiQQResponse<AiQQDetectmultifaceResponse>) JSON.parse(response.body().toString());
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
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("" + TAG, "onResponse: " + response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        aiQQResponse = (AiQQResponse<AiQQFaceVerifyResponse>) JSON.parse(response.body().toString());
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
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("" + TAG, "onResponse: " + response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        aiQQResponse = (AiQQResponse<AiQQNewPersonResponse>) JSON.parse(response.body().toString());
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
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("" + TAG, "onResponse: " + response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        aiQQResponse = (AiQQResponse<AiQQGetPersonIdsRequest>) JSON.parse(response.body().toString());
        return aiQQResponse;
    }


}

