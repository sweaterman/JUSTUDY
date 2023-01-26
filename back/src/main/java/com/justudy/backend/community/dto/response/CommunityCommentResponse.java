package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityCommentResponse {
    private Long sequence;
    private MemberEntity member;
    private CommunityEntity community;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Boolean isDeleted;
    private Long parentSeq;

    @Builder
    public CommunityCommentResponse(Long sequence, MemberEntity member, CommunityEntity community,
                                    String content, LocalDateTime createdTime, LocalDateTime modifiedTime,
                                    Boolean isDeleted, Long parentSeq) {
        this.sequence = sequence;
        this.member = member;
        this.community = community;
        this.content = content;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.isDeleted = isDeleted;
        this.parentSeq = parentSeq;
    }

    public static CommunityCommentResponse makeBuilder(CommunityCommentEntity entity) {
        String comment = entity.getContent();
        if (entity.getIsDeleted())
            comment = "삭제된 댓글입니다.";

        return CommunityCommentResponse.builder()
                .sequence(entity.getSequence())
                .member(entity.getMember())
                .community(entity.getCommunity())
                .content(comment)
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .isDeleted(entity.getIsDeleted())
                .parentSeq(entity.getParentSeq())
                .build();
    }
}
