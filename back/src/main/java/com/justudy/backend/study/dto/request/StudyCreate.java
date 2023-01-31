package com.justudy.backend.study.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class StudyCreate {

    private List<StudyFrequencyCreate> frequencies;
    private String topCategory;
    private String bottomCategory;
    private String name;
    private Long leaderSeq;
    private String introduction;
    private Integer personnel;
    private String level;
    private String onlineOffline;
    private String github;
    private String notion;
    private Long imageSequence;
    private String startTime;
    private String leaderName;


    public StudyEntity toEntity() {
        return StudyEntity
                .builder()
                .frequencies(frequencies
                        .stream()
                        .map(StudyFrequencyCreate::toEntity)
                        .collect(Collectors.toList()))
                .category()
                .name(name)
                .leaderSeq(leaderSeq)
                .introduction(introduction)
                .personnel(personnel)
                .level(level)
                .onlineOffline(onlineOffline)
                .isOpen(true)
                .github(github)
                .notion(notion)
                .createdTime(LocalDateTime.now())
                .startTime(startTime)
                .build();
    }
}
