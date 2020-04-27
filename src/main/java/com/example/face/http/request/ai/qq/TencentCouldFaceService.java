package com.example.face.http.request.ai.qq;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.*;
import org.springframework.stereotype.Service;

/**
 * @author fan.li
 * @date 2020-04-19
 * @description
 */
@Service
public class TencentCouldFaceService {
    final static String secretId = "AKIDKPDqCSmdJp66uVv77MJ1SihtoS3PqEjy";
    final static String secretKey = "tDGn4wta193h4B2JJ2VhjP7apoXy9l6A";


    static IaiClient client;


    static {

        Credential cred = new Credential(secretId, secretKey);


        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);


        client = new IaiClient(cred, "", clientProfile);
    }

    public static void main(String[] args) {
        TencentCouldFaceService couldFaceService = new TencentCouldFaceService();
        couldFaceService.getPersionIdsByGroupId(1+"");


    }


    public CreateGroupResponse createGroup(String groupId, String groupName, String tag) {

        CreateGroupRequest req = new CreateGroupRequest();
        req.setGroupId(groupId);
        req.setFaceModelVersion("3.0");
        req.setTag(groupName);
        req.setGroupName(tag);

        CreateGroupResponse resp = null;
        try {
            resp = client.CreateGroup(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        System.out.println(CreateGroupRequest.toJsonString(resp));
        return resp;
    }

    public CreatePersonResponse createPerson(String groupId, String personId, String base64Image, String personName) {

        CreatePersonRequest req = new CreatePersonRequest();
        req.setGroupId(groupId);
        req.setImage(base64Image);
        req.setPersonId(personId);
        req.setPersonName(personName);
        CreatePersonResponse resp = null;
        try {
            resp = client.CreatePerson(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        System.out.println(CreatePersonResponse.toJsonString(resp));
        return resp;
    }



    public VerifyPersonResponse verifyPerson(String personId, String image) {
        VerifyPersonRequest request = new VerifyPersonRequest();
        request.setImage(image);
        request.setPersonId(personId);

        VerifyPersonResponse resp = null;
        try {
            resp = client.VerifyPerson(request);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        System.out.println(VerifyPersonResponse.toJsonString(resp));
        return resp;
    }


    public GetPersonListResponse getPersionIdsByGroupId(String groupId) {
        GetPersonListRequest request = new GetPersonListRequest();
        request.setGroupId(groupId);
        request.setLimit(1000L);
        request.setOffset(0L);

        GetPersonListResponse resp = null;
        try {
            resp = client.GetPersonList(request);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        System.out.println(GetPersonListResponse.toJsonString(resp));
        return resp;
    }


}

