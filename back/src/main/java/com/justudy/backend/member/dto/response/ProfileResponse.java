package com.justudy.backend.member.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ProfileResponse {

    private String nickname;

    private String[] category;

    private Long imageSequence;

    private String dream;

    private String introduction;

    private String level;

    private Integer badgeCount;

    @Builder
    public ProfileResponse(String nickname, String[] category,
                           Long imageSequence,
                           String dream, String introduction,
                           String level, Integer badgeCount) {
        this.nickname = nickname;
        this.category = category;
        this.imageSequence = imageSequence;
        this.dream = dream;
        this.introduction = introduction;
        this.level = level;
        this.badgeCount = badgeCount;
    }
}
