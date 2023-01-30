package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityResponse {
    private Long sequence;
    private MemberEntity member;
    private String category;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer loveCount;

    @Builder
    public CommunityResponse(Long sequence, MemberEntity member,
                             String category, String title, String content, Integer viewCount,
                             LocalDateTime createdTime, LocalDateTime modifiedTime, Integer loveCount) {
        this.sequence = sequence;
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.loveCount = loveCount;
    }
    public static CommunityResponse makeBuilder(CommunityEntity entity) {
        return makeBuilder(entity,0);
    }
    public static CommunityResponse makeBuilder(CommunityEntity entity, Integer loveCount) {
        return CommunityResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getMember())
                .category(entity.getCategory().getName())
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .loveCount(loveCount)
                .build();
    }
}
