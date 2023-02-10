package com.justudy.backend.study.dto.response.community;

import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudyCommunityDetailResponse {
    private Long sequence;
    private Long memberSequence;
    private String nickname;
    private Long studySeq;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Integer loveCount;

    private boolean isWriter;

    private boolean isBookmarked;

    private boolean isLoved;
    public static StudyCommunityDetailResponse makeBuilder(StudyCommunityEntity entity,
                                                           boolean isWriter, boolean isBookmarked, boolean isLoved) {
        return StudyCommunityDetailResponse.builder()
                .sequence(entity.getSequence())
                .memberSequence(entity.getMember().getSequence())
                .nickname(entity.getMember().getNickname())
                .studySeq(entity.getStudy().getSequence())
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
