package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityCommentEdit {
    private Long sequence;
    private String memberNickName;
    private Long communitySeq;
    private String content;
    private Long parentSeq;


    public CommunityCommentEntity toEntity() {
        return CommunityCommentEntity.builder()
//                .member(memberSeq)
//                .community(communitySeq)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }

    public CommunityCommentEntity toEntity(Long sequence) {
        return CommunityCommentEntity.builder()
                .sequence(sequence)
//                .member(memberSeq)
//                .community(communitySeq)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
