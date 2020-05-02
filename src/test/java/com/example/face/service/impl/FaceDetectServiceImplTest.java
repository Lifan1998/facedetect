package com.example.face.service.impl;

import com.example.face.service.FaceDetectService;
import com.example.face.util.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
public class FaceDetectServiceImplTest {
    @Autowired
    FaceDetectService faceDetectService;

    @Test
    public void testFace() {
        File file = new File("/Users/apple/Downloads/testimage.jpg");

        List<String> list = faceDetectService.getBase64ImageStringList(ImageUtils.encodeFileToBase64Binary(file));

        ImageUtils.base64ToFile(list.get(0));
        System.out.println(list.size());
    }

}