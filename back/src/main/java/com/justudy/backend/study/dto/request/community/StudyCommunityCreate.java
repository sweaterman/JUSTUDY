package com.justudy.backend.study.dto.request.community;

import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudyCommunityCreate {
    private String category;
    private String title;
    private String content;

    public StudyCommunityEntity toEntity() {
        return StudyCommunityEntity.builder()
                .title(title)
                .content(content)
                .isHighlighted(false)
                .build();
    }
}
