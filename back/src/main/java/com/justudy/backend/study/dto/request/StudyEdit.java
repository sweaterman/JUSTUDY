package com.justudy.backend.study.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import com.justudy.backend.studyMember.dto.request.StudyMemberEdit;
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
    private List<StudyMemberEdit> studyMembers;
    private List<StudyResumeEdit> resumes;
    private List<StudyFrequencyEdit> frequencies;
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


    public StudyEntity toEntity(Long leaderSeq) {
        return StudyEntity
                .builder()
                .sequence(sequence)
                .studyMembers(studyMembers)
                .resumes(resumes)
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
                .modifiedTime(LocalDateTime.now())
                .startTime(startTime)
                .build();
    }
}
