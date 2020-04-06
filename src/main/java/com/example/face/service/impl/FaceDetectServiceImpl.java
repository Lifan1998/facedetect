package com.example.face.service.impl;

import com.example.face.http.request.ai.qq.AlQQApiService;
import com.example.face.http.request.ai.qq.request.AiQQDetectmultifaceRequest;
import com.example.face.http.request.ai.qq.response.AiQQDetectmultifaceResponse;
import com.example.face.http.request.ai.qq.response.AiQQResponse;
import com.example.face.service.FaceDetectService;
import com.example.face.util.ImageUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-04-06
 * @description
 */

@Service
public class FaceDetectServiceImpl implements FaceDetectService {

    @Autowired
    AlQQApiService alQQApiService;
    /**
     * 获取人脸组
     *
     * @param base64Image
     * @return
     */
    @Override
    public List<String> getBase64ImageStringList(String base64Image) {
        List<String> base64ImageList = new ArrayList<>();
        // 1. 多人脸处理
        AiQQDetectmultifaceRequest request = new AiQQDetectmultifaceRequest();
        request.setImage(base64Image);
        request.setTime_stamp((int) (System.currentTimeMillis()/1000));
        AiQQResponse<AiQQDetectmultifaceResponse> response = alQQApiService.faceDetectmultiface(request);

        // 判断有没有人脸
        if (!response.getData().getFace_list().isEmpty()) {

            // 判断是不是单人图
            if (response.getData().getFace_list().size() == 1) {
                base64ImageList.add(base64Image);
                return base64ImageList;
            }
            base64ImageList = response.getData().getFace_list().stream()
                    .map(faceLocation -> {
                        File file = ImageUtils.base64ToFile(base64Image);
                        List<File> fileList =  new ArrayList<>();
                        try {
                            fileList =  Thumbnails.of(file)
                                    .sourceRegion(faceLocation.getX1().intValue(), faceLocation.getY1().intValue(), faceLocation.getX2().intValue(), faceLocation.getY2().intValue())
                                    .outputFormat("jpeg").asFiles(Rename.PREFIX_DOT_THUMBNAIL);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 读取文件转base64
                        // TODO 图片选组
                        File targetFile = fileList.stream().findFirst().get();

                        return ImageUtils.encodeFileToBase64Binary(targetFile);
                    }).collect(Collectors.toList());

        }
        return base64ImageList;
    }
}

