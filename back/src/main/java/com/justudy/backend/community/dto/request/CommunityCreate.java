package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
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
