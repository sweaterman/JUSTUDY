package com.justudy.backend.study.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.study.domain.StudyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyCreate {

    private List<StudyFrequencyCreate> frequency;
    private String topCategory;
    private String bottomCategory;
    private String name;
    private Long leaderSeq;
    private String introduction;
    private Integer population;
    private String level;
    private String meeting;
    private String github;
    private String notion;
    private Long imageSequence;
    private String startTime;
    private String leader;

    public void changeLeaderSeq(Long leaderSeq) {
        this.leaderSeq = leaderSeq;
    }

    public StudyEntity toEntity(CategoryEntity categoryEntity) {
        //frequency는 따로 저장
        return StudyEntity
                .builder()
                .category(categoryEntity)
                .name(name)
                .leaderSeq(leaderSeq)
                .introduction(introduction)
                .population(population)
                .level(level)
                .meeting(meeting)
                .isOpen(true)
                .github(github)
                .notion(notion)
                .createdTime(LocalDateTime.now())
                .startTime(startTime)
                .onAir(false)
                .build();
    }
}
