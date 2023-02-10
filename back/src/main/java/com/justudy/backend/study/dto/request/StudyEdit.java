package com.justudy.backend.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyEdit {
    private Long sequence;
    private List<StudyFrequencyCreate> frequency;
    private String topCategory;
    private String bottomCategory;
    private String name;
    private String introduction;
    private Integer population;
    private String level;
    private String meeting;
    private Boolean isOpen;
    private String github;
    private String notion;
    private String startTime;
    private String leader;



}
