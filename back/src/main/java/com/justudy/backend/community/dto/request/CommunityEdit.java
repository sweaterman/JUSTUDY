package com.justudy.backend.community.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityEdit {
    private Long sequence;
    private Long member_seq;
    private Long category_seq;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime modifiedTime;
    @Builder
    public CommunityEdit(Long sequence, Long member_seq, Long category_seq, String title, String content, Integer viewCount,LocalDateTime modifiedTime) {
        this.sequence = sequence;
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.modifiedTime = modifiedTime;
    }
}
