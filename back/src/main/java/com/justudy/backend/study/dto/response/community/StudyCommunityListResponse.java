package com.justudy.backend.study.dto.response.community;

import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudyCommunityListResponse {

    private Long sequence;

    private String title;

    private String nickname;

    private LocalDateTime createdTime;

    private boolean isHighlighted;

    private Integer viewCount;

    private Integer loveCount;

    @Builder
    public StudyCommunityListResponse(Long sequence, String title, String nickname,
                                      LocalDateTime createdTime, boolean isHighlighted,
                                      Integer viewCount, Integer loveCount) {
        this.sequence = sequence;
        this.title = title;
        this.nickname = nickname;
        this.createdTime = createdTime;
        this.isHighlighted = isHighlighted;
        this.viewCount = viewCount;
        this.loveCount = loveCount;
    }

    public StudyCommunityListResponse(StudyCommunityEntity communityEntity) {
        this.sequence = communityEntity.getSequence();
        this.title = communityEntity.getTitle();
        this.nickname = communityEntity.getMember().getNickname(); //memberEntity
        this.createdTime = communityEntity.getCreatedTime();
        this.isHighlighted = communityEntity.getIsHighlighted();
        this.viewCount = communityEntity.getViewCount(); //조회수
        this.loveCount = communityEntity.getLoveCount() + communityEntity.getWeekLoveCount(); //기존 좋아요 + 일주일간 좋아요
    }
}
