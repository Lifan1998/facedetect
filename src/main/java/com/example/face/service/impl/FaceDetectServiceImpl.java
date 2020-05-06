package com.example.face.service.impl;

import com.example.face.http.request.ai.qq.AlQQApiService;
import com.example.face.http.request.ai.qq.request.AiQQDetectmultifaceRequest;
import com.example.face.http.request.ai.qq.response.AiQQDetectmultifaceResponse;
import com.example.face.http.request.ai.qq.response.AiQQResponse;
import com.example.face.service.FaceDetectService;
import com.example.face.util.ImageUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("getBase64ImageStringList base64Image size {}", base64Image.length());
        // 图片落库
        File file1 = ImageUtils.base64ToFile(base64Image);
        // 图片压缩
        if ((base64Image.length() - 2 ) * 0.75 >= 1000 * 1024) {
            file1 = ImageUtils.handleFileSize(file1, 1000 * 1024);
            base64Image = ImageUtils.encodeFileToBase64Binary(file1);
        }

        File targetFile = file1;
        List<String> base64I   mageList = new ArrayList<>();
        // 1. 多人脸处理
        AiQQDetectmultifaceRequest request = new AiQQDetectmultifaceRequest();
        base64Image = ImageUtils.encodeFileToBase64Binary(targetFile);
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
                        File file = targetFile;
                        List<File> fileList =  new ArrayList<>();
                        try {
                            fileList =  Thumbnails.of(file)
                                    .scale(1)
                                    .sourceRegion(faceLocation.getX1().intValue(), faceLocation.getY1().intValue(), faceLocation.getX2().intValue(), faceLocation.getY2().intValue())
                                    .outputFormat("jpeg").asFiles(Rename.PREFIX_DOT_THUMBNAIL);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 读取文件转base64
                        // TODO 图片选组
                        File targetFile_ = fileList.stream().findFirst().get();
                        // 图片落库


                        return ImageUtils.encodeFileToBase64Binary(targetFile_);
                    }).collect(Collectors.toList());

        }
        if (base64ImageList.isEmpty()) {
            throw new RuntimeException("打卡失败，图片未找到人脸信息");
        }
        return base64ImageList;
    }
}

