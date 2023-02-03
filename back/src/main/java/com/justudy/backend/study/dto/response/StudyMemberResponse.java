package com.justudy.backend.study.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Member;

@Getter
@Builder
@AllArgsConstructor
public class StudyMemberResponse {
    private Long sequence;
    private Long memberSeq;
    private Long studySeq;
    private Boolean alarm;
    private Integer badge;
    private String name;
    private String nickName;

    public static StudyMemberResponse makeBuilder(StudyMemberEntity entity) {
        return StudyMemberResponse.builder()
                .sequence(entity.getSequence())
                .memberSeq(entity.getMember().getSequence())
                .studySeq(entity.getStudy().getSequence())
                .alarm(entity.getAlarm())
                .badge(entity.getBadge())
                .name(entity.getMember().getUsername())
                .nickName(entity.getMember().getNickname())
                .build();
    }
}
