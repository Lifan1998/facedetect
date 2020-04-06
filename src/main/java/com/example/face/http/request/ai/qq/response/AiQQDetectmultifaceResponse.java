package com.example.face.http.request.ai.qq.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */
@Data
public class AiQQDetectmultifaceResponse {
    List<FaceLocation> face_list;

    @Data
    public static class FaceLocation {
        BigDecimal x1;
        BigDecimal y1;
        BigDecimal x2;
        BigDecimal y2;
    }

}

