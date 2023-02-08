package com.justudy.backend.study.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.domain.StudyResumeRespond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyResumeCreate {
    private Long studySeq;
    private Long memberSeq;
    private String content;

    public void changeMemberSeq(Long memberSeq) {
        this.memberSeq = memberSeq;
    }

    public StudyResumeEntity toEntity(StudyEntity studyEntity, MemberEntity memberEntity) {
        return StudyResumeEntity
                .builder()
                .study(studyEntity)
                .member(memberEntity)
                .content(content)
                .createdTime(LocalDateTime.now())
                .respond(StudyResumeRespond.WAITING)
                .build();
    }
}
