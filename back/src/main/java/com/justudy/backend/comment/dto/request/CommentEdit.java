package com.justudy.backend.comment.dto.request;

import com.justudy.backend.comment.domain.CommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentEdit {
    private Long sequence;
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private Long parentSeq;

    @Builder
    public CommentEdit(Long sequence, Long memberSeq, Long communitySeq, String content, Long parentSeq) {
        this.sequence = sequence;
        this.memberSeq = memberSeq;
        this.communitySeq = communitySeq;
        this.content = content;
        this.parentSeq = parentSeq;
    }

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
