package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyCreate {
    private List<StudyMemberEntity> studyMembers;
    private List<ResumeEntity> resume;
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
                .studyMembers(studyMembers)
                .resume(resume)
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
                .createdTime(LocalDateTime.now())
                .startTime(startTime)
                .build();
    }
}
