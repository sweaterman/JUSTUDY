package com.justudy.backend.member.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberEdit {

    @NotBlank
    private String nickname;

    private String password;

    private String passwordCheck;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String region;

    private String dream;

    private String[] category;

    private String introduction;

    @Builder
    public MemberEdit(String nickname, String password, String passwordCheck,
                      String phone, String email,
                      String region, String dream,
                      String[] category, String introduction) {
        this.nickname = nickname;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.phone = phone;
        this.email = email;
        this.region = region;
        this.dream = dream;
        this.category = category;
        this.introduction = introduction;
    }
}
