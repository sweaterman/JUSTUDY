package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class StudyResponse {
    private Long sequence;
    private List<StudyMemberResponse> member;
    private List<Long> resumeSeq;
    private List<Long> frequency;
    private String topCategory;
    private String bottomCategory;
    private String name;
    private Long leaderSeq;
    private String introduction;
    private Integer population;
    private String level;
    private String onlineOffline;
    private Boolean isOpen;
    private String github;
    private String notion;
    private Long imageSequence;
    private LocalDateTime createdTime;
    private String startTime;
    private Boolean checkMore;

    public static StudyResponse makeBuilder(StudyEntity entity) {
        return StudyResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getStudyMembers()
                        .stream()
                        .map(StudyMemberResponse::makeBuilder)
                        .sorted(Comparator.comparing(StudyMemberResponse::getBadge).reversed())
                        .collect(Collectors.toList()))
                .resumeSeq(entity.getResumes()
                        .stream()
                        .map(StudyResumeEntity::getSequence)
                        .collect(Collectors.toList()))
                .frequency(entity.getFrequency()
                        .stream()
                        .map(StudyFrequencyEntity::getSequence)
                        .collect(Collectors.toList()))
                .topCategory(entity.getCategory().getParentCategory().getKey())
                .bottomCategory(entity.getCategory().getKey())
                .name(entity.getName())
                .leaderSeq(entity.getLeaderSeq())
                .introduction(entity.getIntroduction())
                .population(entity.getPopulation())
                .level(entity.getLevel())
                .onlineOffline(entity.getOnlineOffline())
                .isOpen(entity.getIsOpen())
                .github(entity.getGithub())
                .notion(entity.getNotion())
                .imageSequence(entity.getImageFile().getSequence())
                .createdTime(entity.getCreatedTime())
                .startTime(entity.getStartTime())
                .build();
    }

}
