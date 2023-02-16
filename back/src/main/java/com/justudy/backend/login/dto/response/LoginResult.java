package com.justudy.backend.login.dto.response;

import com.justudy.backend.member.domain.MemberRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginResult {

    private Long loginSequence;
    private String password;
    private String nickname;
    private MemberRole role;

    public LoginResult(String nickname) {
        this.nickname = nickname;
    }
}

