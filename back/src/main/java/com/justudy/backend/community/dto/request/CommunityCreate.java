package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityCreate {
    private Long member_seq;
    private Long category_seq;
    private String title;
    private String content;
    private LocalDateTime createdTime;

    @Builder
    public CommunityCreate(Long member_seq, Long category_seq, String title, String content, LocalDateTime createdTime) {
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
    }

    public CommunityEntity toEntity() {
        return CommunityEntity.builder()
                .member_seq(member_seq)
                .category_seq(category_seq)
                .title(title)
                .content(content)
                .viewCount(0)
                .createdTime(createdTime)

                .build();
    }
}
