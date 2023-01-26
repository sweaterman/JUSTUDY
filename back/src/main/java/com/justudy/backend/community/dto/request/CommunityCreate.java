package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityCreate {
    private MemberEntity member;
    private Long category_seq;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private Boolean isHighlighted;

    public CommunityEntity toEntity() {
        return CommunityEntity.builder()
                .member(member)
                .category_seq(category_seq)
                .title(title)
                .content(content)
                .viewCount(0)
                .createdTime(createdTime)
                .isHighlighted(isHighlighted)
                .build();
    }
}
