package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Getter
@NoArgsConstructor
public class CommunityCommentResponse {
    private Long sequence;
    private Long memberSeq;
    private Long communitySeq;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Boolean isDeleted;
    private Long parentSeq;

    @Builder
    public CommunityCommentResponse(Long sequence, Long memberSeq, Long communitySeq,
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

    public static CommunityCommentResponse makeBuilder(CommunityCommentEntity entity) {
        String comment = entity.getContent();
        if (entity.getIsDeleted() == true)
            comment = "삭제된 댓글입니다.";

        return CommunityCommentResponse.builder()
                .sequence(entity.getSequence())
                .memberSeq(entity.getMember().getSequence())
                .communitySeq(entity.getCommunity().getSequence())
                .content(comment)
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .isDeleted(entity.getIsDeleted())
                .parentSeq(entity.getParentSeq())
                .build();
    }
}
