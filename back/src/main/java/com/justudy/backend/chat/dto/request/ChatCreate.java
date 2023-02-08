package com.justudy.backend.chat.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ChatCreate {
    private Long from;
    private Long to;
    private Long room;
    private String content;
    private Boolean isRead;
    private LocalDateTime fromTime;
    private LocalDateTime readTime;

    public StudyEntity toEntity(CategoryEntity categoryEntity) {
        //todo to entity
        return StudyEntity
                .builder()
                .build();
    }
}
