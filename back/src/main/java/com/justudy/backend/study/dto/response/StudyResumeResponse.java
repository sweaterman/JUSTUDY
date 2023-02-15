package com.justudy.backend.study.dto.response;

import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StudyResumeResponse {
    private Long sequence;
    private Long memberSeq;
    private Long studySeq;
    private String content;
    private String createdTime;
    private Long imageSequence;
    private String nickName;

    public static StudyResumeResponse makeBuilder(StudyResumeEntity entity) {
        MemberEntity memberEntity = entity.getMember();
        return StudyResumeResponse.builder()
                .sequence(entity.getSequence())
                .memberSeq(entity.getMember().getSequence())
                .studySeq(entity.getStudy().getSequence())
                .content(entity.getContent())
                .createdTime(DateChanger.format(entity.getCreatedTime()))
                .imageSequence(memberEntity.getImageFile().getSequence())
                .nickName(memberEntity.getNickname())
                .build();
    }
}
