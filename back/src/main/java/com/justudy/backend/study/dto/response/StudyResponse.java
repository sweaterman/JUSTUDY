package com.justudy.backend.study.dto.response;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
@AllArgsConstructor
@Builder
public class StudyResponse {
    private Long sequence;
    private String name;
    private String leader;
    private Integer population;
    private String topCategory;
    private String bottomCategory;
    private List<Long> member;
    private String level;
    private String meeting;
    private Boolean isOpen;
    private String startTime;
    private Long imageSequence;


    public static StudyResponse makeBuilder(StudyEntity entity) {
        //todo work?

        log.info("로그1 {}", entity.getSequence());
        log.info("로그1 {}", entity.getStudyMembers().size());
        for (StudyMemberEntity studyMemberEntity : entity.getStudyMembers()
        ) {
            log.info("로그 {}", entity.getLeaderSeq().longValue());
            if (entity.getLeaderSeq().longValue() == studyMemberEntity.getMember().getSequence().longValue()) {
                log.info("로그s {}");
            }
        }
        String leader = entity.getStudyMembers()
                .stream()
                .filter(studyMemberEntity -> entity.getLeaderSeq().longValue() == studyMemberEntity.getMember().getSequence().longValue())

                .findFirst()
                .orElseThrow(InvalidRequest::new)
                .getMember().getNickname();

        //todo image 추가
        return StudyResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getStudyMembers()
                        .stream()
                        .map(StudyMemberEntity::getSequence)
                        .collect(Collectors.toList()))
                .topCategory(entity.getCategory().getParentCategory().getValue())
                .bottomCategory(entity.getCategory().getValue())
                .name(entity.getName())
                .population(entity.getPopulation())
                .level(entity.getLevel())
                .meeting(entity.getMeeting())
                .isOpen(entity.getIsOpen())
//                .imageSequence(entity.getImageFile().getSequence())
                .startTime(entity.getStartTime())
                .leader(leader)
                .build();
    }

}
