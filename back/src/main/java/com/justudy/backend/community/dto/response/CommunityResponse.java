package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityResponse {
    private Long sequence;
    private Long member_seq;
    private Long category_seq;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer love;

    @Builder
    public CommunityResponse(Long sequence, Long member_seq, Long category_seq, String title, String content, Integer viewCount, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer love) {
        this.sequence = sequence;
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.love = love;
    }

    public static CommunityResponse makeBuilder(CommunityEntity entity) {
        return CommunityResponse.builder()
                .sequence(entity.getSequence())
                .member_seq(entity.getMember_seq())
                .category_seq(entity.getCategory_seq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }
}
