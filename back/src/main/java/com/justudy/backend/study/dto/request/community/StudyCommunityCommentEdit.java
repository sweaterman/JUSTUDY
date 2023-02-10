package com.justudy.backend.study.dto.request.community;

import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyCommunityCommentEdit {
    private Long sequence;
    private String memberNickName;
    private Long communitySeq;
    private String content;
    private Long parentSeq;


    public StudyCommunityCommentEntity toEntity() {
        return StudyCommunityCommentEntity.builder()
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }

    public StudyCommunityCommentEntity toEntity(Long sequence) {
        return StudyCommunityCommentEntity.builder()
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
