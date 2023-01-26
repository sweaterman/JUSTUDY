package com.justudy.backend.study.dto.response;

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
public class StudyResponse {
    private Long sequence;
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
    private LocalDateTime createdTime;
    private String startTime;

    public static StudyResponse makeBuilder(StudyEntity entity) {
        return StudyResponse.builder()
                .sequence(entity.getSequence())
                .studyMembers(entity.getStudyMembers())
                .resume(entity.getResume())
                .leaderSeq(entity.getLeaderSeq())
                .introduction(entity.getIntroduction())
                .personnel(entity.getPersonnel())
                .level(entity.getLevel())
                .onlineOffline(entity.getOnlineOffline())
                .isOpen(entity.getIsOpen())
                .github(entity.getGithub())
                .notion(entity.getNotion())
                .createdTime(entity.getCreatedTime())
                .startTime(entity.getStartTime())
                .build();
    }
}
