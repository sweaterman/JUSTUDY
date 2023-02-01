package com.justudy.backend.member.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MypageResponse {

    private String nickname;

    private String[] category;

    private String dream;

    private String status;

    private Long imageSequence;

    private int badgeCount;

    private String level;

    @Builder
    public MypageResponse(String nickname, String[] category,
                          String dream, String status, Long imageSequence,
                          int badgeCount, String level) {
        this.nickname = nickname;
        this.category = category;
        this.dream = dream;
        this.status = status;
        this.imageSequence = imageSequence;
        this.badgeCount = badgeCount;
        this.level = level;
    }
}
