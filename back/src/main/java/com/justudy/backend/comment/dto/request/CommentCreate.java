package com.justudy.backend.comment.dto.request;

import com.justudy.backend.comment.domain.CommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentCreate {
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private Long parentSeq;

    @Builder
    public CommentCreate(Long memberSeq, Long communitySeq, String content, Long parentSeq) {
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
}
