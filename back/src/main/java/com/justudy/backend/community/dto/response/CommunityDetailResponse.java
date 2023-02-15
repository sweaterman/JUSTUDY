package com.justudy.backend.community.dto.response;

import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.community.domain.CommunityEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommunityDetailResponse {
    private Long sequence;
    private Long memberSequence;
    private String nickname;
    private CategoryResponse category;
    private String title;
    private String content;
    private Integer viewCount;
    private String createdTime;
    private String modifiedTime;
    private Integer loveCount;

    private boolean isWriter;

    private boolean isBookmarked;

    private boolean isLoved;


    @Builder
    public CommunityDetailResponse(Long sequence, Long memberSequence, String nickname,
                                   CategoryResponse category, String title, String content, Integer viewCount,
                                   LocalDateTime createdTime, LocalDateTime modifiedTime, Integer loveCount,
                                   boolean isWriter, boolean isBookmarked, boolean isLoved) {
        this.sequence = sequence;
        this.memberSequence = memberSequence;
        this.nickname = nickname;
        this.category = category;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdTime = DateChanger.format(createdTime);
        this.modifiedTime = DateChanger.format(modifiedTime);
        this.loveCount = loveCount;

        this.isWriter = isWriter;
        this.isBookmarked = isBookmarked;
        this.isLoved = isLoved;
    }

    public static CommunityDetailResponse makeBuilder(CommunityEntity entity) {
        return CommunityDetailResponse.builder()
                .sequence(entity.getSequence())
                .memberSequence(entity.getMember().getSequence())
                .nickname(entity.getMember().getNickname())
                .category(new CategoryResponse(entity.getCategory()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .loveCount(entity.getLoveCount() + entity.getWeekLoveCount())
                .build();
    }

    public static CommunityDetailResponse makeBuilder(CommunityEntity entity,
                                                      boolean isWriter, boolean isBookmarked, boolean isLoved) {
        return CommunityDetailResponse.builder()
                .sequence(entity.getSequence())
                .memberSequence(entity.getMember().getSequence())
                .nickname(entity.getMember().getNickname())
                .category(new CategoryResponse(entity.getCategory()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .loveCount(entity.getLoveCount() + entity.getWeekLoveCount())
                .isWriter(isWriter)
                .isBookmarked(isBookmarked)
                .isLoved(isLoved)
                .build();
    }
}
