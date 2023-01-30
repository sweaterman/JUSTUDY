package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityCreate {
    private String category;
    private String title;
    private String content;
    private Boolean isHighlighted;

    public CommunityEntity toEntity() {
        return CommunityEntity.builder()
                .title(title)
                .content(content)
                .isHighlighted(isHighlighted)
                .build();
    }
}
