package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
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
    private List<StudyMemberEntity> studyMembers;
    private List<StudyResumeEntity> resumes;
    private List<StudyFrequencyEntity> frequencies;
    private Long category_seq;
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
                .sequence(sequence)
                .studyMembers(studyMembers)
                .resumes(resumes)
                .category_seq(category_seq)
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
