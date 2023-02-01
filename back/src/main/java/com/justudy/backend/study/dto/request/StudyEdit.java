package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyEdit {
    private Long sequence;
    private List<Long> member;
    private List<Long> resume;
    private List<StudyFrequencyCreate> frequency;
    private String topCategory;
    private String bottomCategory;
    private String name;
    private String leader;
    private String introduction;
    private Integer population;
    private String level;
    private String onlineOffline;
    private Boolean isOpen;
    private String github;
    private String notion;
    private String startTime;



}
