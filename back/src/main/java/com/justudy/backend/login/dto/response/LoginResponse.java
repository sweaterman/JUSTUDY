package com.justudy.backend.login.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginResponse {

    private Long loginSequence;

    private String password;
    private String nickname;

    public LoginResponse(String nickname) {
        this.nickname = nickname;
    }
}

