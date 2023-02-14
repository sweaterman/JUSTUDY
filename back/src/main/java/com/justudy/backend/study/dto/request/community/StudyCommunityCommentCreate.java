package com.justudy.backend.study.dto.request.community;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyCommunityCommentCreate {
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private Long parentSeq;

    public Object toEntity(StudyCommunityEntity communityEntity, MemberEntity memberEntity, Integer group, Integer orderResult, Long parentSeq, Integer step) {
        return StudyCommunityCommentEntity.builder()
                .member(memberEntity)
                .community(communityEntity)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .isDeleted(false)
                .group(group)
                .step(step)
                .order(orderResult)
                .childNumber(0)
                .build();
    }

    public void changeMemberSeq(Long loginSequence) {
        this.memberSeq = loginSequence;
    }
    public void changecommunitySeq(Long communitySeq) {
        this.communitySeq = communitySeq;
    }
}
