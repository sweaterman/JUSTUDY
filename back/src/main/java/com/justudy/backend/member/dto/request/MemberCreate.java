package com.justudy.backend.member.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberCreate {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotBlank
    private String ssafyId;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    private String region;

    private String dream;

    private String[] categories;

    private String introduction;

    @Builder
    public MemberCreate(String userId, String password, String passwordCheck,
                        String username, String nickname, String ssafyId, String phone,
                        String email, String region, String dream,
                        String[] category, String introduction) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
        this.nickname = nickname;
        this.ssafyId = ssafyId;
        this.phone = phone;
        this.email = email;
        this.region = region;
        this.dream = dream;
        this.categories = category;
        this.introduction = introduction;
    }
}
