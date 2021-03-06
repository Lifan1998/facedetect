package com.example.face.http.request.ai.qq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.face.http.request.ai.qq.request.AiQQGetGroupIdsRequest;
import com.example.face.http.request.ai.qq.request.AiQQGetPersonIdsRequest;
import com.example.face.http.request.ai.qq.request.AiQQNewPersonRequest;
import com.example.face.http.request.ai.qq.request.AiQQRequest;
import com.example.face.http.request.ai.qq.response.*;
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
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error faceDetectmultiface {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);

        AiQQResponse<AiQQDetectmultifaceResponse> aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQDetectmultifaceResponse>>(){});
        log.info("info  faceDetectmultiface  api {}", "aiQQResponse: " + aiQQResponse);
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

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error faceVerify {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQFaceVerifyResponse>>(){});
        log.info("info  faceVerify  api {}", "aiQQResponse: " + aiQQResponse);
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

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error  newPerson {}", "e: " + e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQNewPersonResponse>>(){});
        log.info("info  newPerson  api {}", "aiQQResponse: " + aiQQResponse);
        return aiQQResponse;
    }

    public AiQQResponse<AiQQNewPersonResponse> getPersonIds(AiQQRequest aiQQRequest) {
        AiQQResponse<AiQQNewPersonResponse> aiQQResponse;


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

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQNewPersonResponse>>(){});
        log.info("info  newPerson  api {}", "aiQQResponse: " + aiQQResponse);

        return aiQQResponse;
    }

    public AiQQResponse<AiQQGetGroupIdsResponse> getGroupids(AiQQRequest aiQQRequest) {
        AiQQResponse<AiQQGetGroupIdsResponse> aiQQResponse;


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = HttpUtils.getOkHttpRequest(aiQQRequest);

        Request request = new Request.Builder()
                .url("https://api.ai.qq.com/fcgi-bin/face/face_getgroupids")
                .post(requestBody)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(responseString);
        aiQQResponse = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<AiQQResponse<AiQQGetGroupIdsResponse>>(){});
        log.info("info  getGroupids  api {}", "aiQQResponse: " + aiQQResponse);

        return aiQQResponse;
    }



    public static void main(String[] args) {
        AiQQNewPersonRequest aiQQNewPersonRequest = new AiQQNewPersonRequest();
        aiQQNewPersonRequest.setGroup_ids(1 + "");
        aiQQNewPersonRequest.setImage("1111");
        aiQQNewPersonRequest.setNonce_str("fa577ce340859f9fe");
        aiQQNewPersonRequest.setPerson_id(1 + "");
        aiQQNewPersonRequest.setPerson_name("111");
        AiQQResponse<AiQQNewPersonResponse> newPersonResponse = new AlQQApiService().newPerson(aiQQNewPersonRequest);
        if (newPersonResponse.getRet() != 0) {

        }

        AiQQGetGroupIdsRequest aiQQGetGroupIdsRequest = new AiQQGetGroupIdsRequest();
        aiQQGetGroupIdsRequest.setNonce_str("fa577ce340859");
        AiQQResponse<AiQQGetGroupIdsResponse> getGroupIdsResponse = new AlQQApiService().getGroupids(aiQQGetGroupIdsRequest);




    }

}

