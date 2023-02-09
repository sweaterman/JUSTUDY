package com.justudy.backend.member.dto.request;

import lombok.Data;

@Data
public class MatterMostRequest {

    private String device_id;
    private String login_id;
    private String password;
    private String token;

    public MatterMostRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }
}
