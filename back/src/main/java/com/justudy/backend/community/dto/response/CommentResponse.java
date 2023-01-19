package com.justudy.backend.community.dto.response;

import com.justudy.backend.comment.domain.CommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long sequence;
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Boolean isDeleted;
    private Long parentSeq;

    @Builder
    public CommentResponse(Long sequence, Long memberSeq, Long communitySeq,
                           String content, LocalDateTime createdTime, LocalDateTime modifiedTime,
                           Boolean isDeleted, Long parentSeq) {
        this.sequence = sequence;
        this.memberSeq = memberSeq;
        this.communitySeq = communitySeq;
        this.content = content;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.isDeleted = isDeleted;
        this.parentSeq = parentSeq;
    }

    public static CommentResponse makeBuilder(CommentEntity entity) {
        String comment = entity.getContent();
        if (entity.getIsDeleted())
            comment = "삭제된 댓글입니다.";

        return CommentResponse.builder()
                .sequence(entity.getSequence())
                .memberSeq(entity.getMemberSeq())
                .communitySeq(entity.getCommunitySeq())
                .content(comment)
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .isDeleted(entity.getIsDeleted())
                .parentSeq(entity.getParentSeq())
                .build();
    }
}
