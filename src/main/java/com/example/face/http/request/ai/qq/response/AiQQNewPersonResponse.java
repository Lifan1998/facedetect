package com.example.face.http.request.ai.qq.response;

import lombok.Data;

/**
 * @author fan.li
 * @date 2020-04-05
 * @description
 */
@Data
public class AiQQNewPersonResponse {
    int suc_group;
    int suc_face;
    String person_id;
    String face_id;
    String group_ids;
}

