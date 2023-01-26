package com.justudy.backend.member.dto.response;

import com.justudy.backend.member.domain.MemberCategoryEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MypageResponse {

    String nickname;

    String[] category;

    String dream;

    String status;

    int badgeCount;

    String level;

    @Builder
    public MypageResponse(String nickname, String[] category,
                          String dream, String status,
                          int badgeCount, String level) {
        this.nickname = nickname;
        this.category = category;
        this.dream = dream;
        this.status = status;
        this.badgeCount = badgeCount;
        this.level = level;
    }
}
