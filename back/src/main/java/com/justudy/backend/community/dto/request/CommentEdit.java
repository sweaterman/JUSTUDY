package com.justudy.backend.community.dto.request;

import com.justudy.backend.comment.domain.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentEdit {
    private Long sequence;
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private Long parentSeq;


    public CommentEntity toEntity() {
        return CommentEntity.builder()
                .memberSeq(memberSeq)
                .communitySeq(communitySeq)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }

    public CommentEntity toEntity(Long sequence) {
        return CommentEntity.builder()
                .sequence(sequence)
                .memberSeq(memberSeq)
                .communitySeq(communitySeq)
                .content(content)
                .parentSeq(parentSeq)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
