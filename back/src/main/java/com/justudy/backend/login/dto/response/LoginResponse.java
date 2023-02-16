package com.justudy.backend.login.dto.response;

import com.justudy.backend.member.domain.MemberRole;
import lombok.Data;

@Data
public class LoginResponse {

    private String nickname;

    private boolean isAdmin;

    public LoginResponse(LoginResult result) {
        this.nickname = result.getNickname();
        this.isAdmin = result.getRole().equals(MemberRole.ADMIN) ? true : false;
    }
}
