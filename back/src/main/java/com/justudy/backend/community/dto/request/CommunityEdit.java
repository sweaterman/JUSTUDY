package com.justudy.backend.community.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityEdit {
    private Long sequence;
    private Long member_seq;
    private Long category_seq;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime modifiedTime;
}
