package com.justudy.backend.study.dto.response;

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
    private Boolean alarm;
    private Integer badge;
    private String name;
    private String nickName;
    private Long imageSequence;

    public static StudyMemberResponse makeBuilder(StudyMemberEntity entity) {
        return StudyMemberResponse.builder()
                .sequence(entity.getSequence())
                .badge(entity.getBadge())
                .alarm(entity.getAlarm())
                .name(entity.getMember().getUsername())
                .nickName(entity.getMember().getNickname())
                .imageSequence(entity.getMember().getImageFile().getSequence())
                .build();
    }
}
