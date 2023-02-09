package com.justudy.backend.member.dto.request;

import lombok.Data;

@Data
public class MMValidateRequest {

    private String mmId;

    private String mmPassword;
}
