package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityListResponse {

    private Long sequence;

    private String title;

    private String nickname;

    private LocalDateTime createdTime;

    private boolean isHighlighted;

    private Integer loveCount;

    @Builder
    public CommunityListResponse(Long sequence, String title, String nickname,
                                 LocalDateTime createdTime,
                                 boolean isHighlighted, Integer loveCount) {
        this.sequence = sequence;
        this.title = title;
        this.nickname = nickname;
        this.createdTime = createdTime;
        this.isHighlighted = isHighlighted;
        this.loveCount = loveCount;
    }

    public CommunityListResponse(CommunityEntity communityEntity) {
        this.sequence = communityEntity.getSequence();
        this.title = communityEntity.getTitle();
        this.nickname = communityEntity.getMember().getNickname(); //memberEntity
        this.createdTime = communityEntity.getCreatedTime();
        this.isHighlighted = communityEntity.getIsHighlighted();
        this.loveCount = communityEntity.getLoveCount() + communityEntity.getWeekLoveCount(); //기존 좋아요 + 일주일간 좋아요
    }
}
