package com.justudy.backend.study.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
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
    private Integer badge;

    public static StudyMemberResponse makeBuilder(StudyMemberEntity entity) {
        return StudyMemberResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getMember())
                .study(entity.getStudy())
                .alarm(entity.getAlarm())
                .badge(entity.getBadge())
                .build();
    }
}
