package com.justudy.backend.study.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyCreate {

    private List<StudyFrequencyEntity> frequencies;
    private CategoryEntity category;
    private String name;
    private Long leaderSeq;
    private String introduction;
    private Integer personnel;
    private String level;
    private String onlineOffline;
    private Boolean isOpen;
    private String github;
    private String notion;
    private String startTime;


    public StudyEntity toEntity() {
        return StudyEntity
                .builder()
                .frequencies(frequencies)
                .category(category)
                .name(name)
                .leaderSeq(leaderSeq)
                .introduction(introduction)
                .personnel(personnel)
                .level(level)
                .onlineOffline(onlineOffline)
                .isOpen(isOpen)
                .github(github)
                .notion(notion)
                .createdTime(LocalDateTime.now())
                .startTime(startTime)
                .build();
    }
}
