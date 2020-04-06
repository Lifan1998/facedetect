package com.example.face.http.request.ai.qq.request;

import com.example.face.http.request.ai.qq.request.AiQQRequest;
import lombok.Data;

/**
 * @author fan.li
 * @date 2020-04-05
 * @description
 */
@Data
public class AiQQNewPersonRequest extends AiQQRequest {
    String group_ids;
    String person_id;
    String image;
    String person_name;
    /**
     * 备注，可不填
     *
     */
    String tag;
}

