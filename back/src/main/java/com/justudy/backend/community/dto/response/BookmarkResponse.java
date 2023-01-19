package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookmarkResponse {
    private Long sequence;
    private Long member_seq;
    private Long category_seq;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer loveCount;

    @Builder
    public BookmarkResponse(Long sequence, Long member_seq, Long category_seq, String title, String content, Integer viewCount, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer loveCount) {
        this.sequence = sequence;
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.loveCount = loveCount;
    }
    public static BookmarkResponse makeBuilder(CommunityEntity entity) {
        return makeBuilder(entity,0);
    }
    public static BookmarkResponse makeBuilder(CommunityEntity entity, Integer loveCount) {
        return BookmarkResponse.builder()
                .sequence(entity.getSequence())
                .member_seq(entity.getMember_seq())
                .category_seq(entity.getCategory_seq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .loveCount(loveCount)
                .build();
    }
}
