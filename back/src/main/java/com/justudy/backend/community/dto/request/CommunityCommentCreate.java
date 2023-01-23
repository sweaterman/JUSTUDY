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
    private MemberEntity member;
    private CommunityEntity community;
    private String content;
    private Long parentSeq;

    public CommunityCommentEntity toEntity() {
        return CommunityCommentEntity.builder()
                .member(member)
                .community(community)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
