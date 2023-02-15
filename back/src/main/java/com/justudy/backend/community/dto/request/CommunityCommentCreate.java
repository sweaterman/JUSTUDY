package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityCommentCreate {
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private Long parentSeq;
//
//    public CommunityCommentEntity toEntity(CommunityEntity communityEntity, MemberEntity memberEntity, Integer commentGroup) {//
//        return CommunityCommentEntity.builder()
//                .member(memberEntity)
//                .community(communityEntity)
//                .content(content)
//                .parentSeq(parentSeq)
//                .createdTime(LocalDateTime.now())
//                .isDeleted(false)
//                .group(commentGroup)
//                .step(0)
//                .order(0)
//                .childNumber(0)
//                .build();
//    }

    public Object toEntity(CommunityEntity communityEntity, MemberEntity memberEntity, Integer group, Integer orderResult, Long parentSeq, Integer step) {
        return CommunityCommentEntity.builder()
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
