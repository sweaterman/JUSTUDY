package com.justudy.backend.study.dto.request.community;

import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudyCommunityCreate {
    private String studySeq;
    private String title;
    private String content;
    private Boolean isHighlighted;

    public StudyCommunityEntity toEntity() {
        return StudyCommunityEntity.builder()
                .title(title)
                .content(content)
                .isHighlighted(isHighlighted)
                .build();
    }
}
