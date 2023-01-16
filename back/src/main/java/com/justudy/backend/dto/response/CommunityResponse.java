package com.justudy.backend.dto.response;

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
    private Boolean bookmark;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer love;
    private Boolean isDeleted;

    @Builder
    public CommunityResponse(Long sequence, Long member_seq, Long category_seq, String title, String content, Integer viewCount, Boolean bookmark, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer love, Boolean isDeleted) {
        this.sequence = sequence;
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.bookmark = bookmark;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.love = love;
        this.isDeleted = isDeleted;
    }
}
