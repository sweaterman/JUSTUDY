package com.justudy.backend.studyMember.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class StudyMemberResponse {
    private Long sequence;
    private MemberEntity member;
    private StudyEntity study;
    private Boolean alarm;
    private Integer king;
    private LocalDateTime createdTime;

    public static StudyMemberResponse makeBuilder(StudyMemberEntity entity) {
        return StudyMemberResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getMember())
                .study(entity.getStudy())
                .alarm(entity.getAlarm())
                .king(entity.getKing())
                .createdTime(entity.getCreatedTime())
                .build();
    }
}
