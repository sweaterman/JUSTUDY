package com.justudy.backend.study.dto.response.community;

import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StudyCommunityDetailResponse {
    private Long sequence;
    private Long memberSequence;
    private String nickname;
    private CategoryResponse category;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer loveCount;

    private boolean isWriter;

    private boolean isBookmarked;

    private boolean isLoved;


    @Builder
    public StudyCommunityDetailResponse(Long sequence, Long memberSequence, String nickname,
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
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.loveCount = loveCount;

        this.isWriter = isWriter;
        this.isBookmarked = isBookmarked;
        this.isLoved = isLoved;
    }

    public static StudyCommunityDetailResponse makeBuilder(StudyCommunityEntity entity,
                                                           boolean isWriter, boolean isBookmarked, boolean isLoved) {
        return StudyCommunityDetailResponse.builder()
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
