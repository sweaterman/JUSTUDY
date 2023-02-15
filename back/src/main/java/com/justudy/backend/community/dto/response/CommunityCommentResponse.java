package com.justudy.backend.community.dto.response;

import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CommunityCommentResponse {
    private Long sequence;
    private String memberNickName;
    private Long communitySeq;
    private String content;
    private String createdTime;
    private String modifiedTime;
    private Boolean isDeleted;
    private Long parentSeq;

    private Boolean isWriter;

    public static CommunityCommentResponse makeBuilder(CommunityCommentEntity entity, Long loginSequence) {
        MemberEntity memberEntity = entity.getMember();
        String comment = entity.getContent();
        String memberNickName = memberEntity.getNickname();
        Boolean isWriter = false;

        //댓글 작성자면 true
        if (loginSequence.longValue() == memberEntity.getSequence().longValue())
            isWriter = true;

        if (entity.getIsDeleted()) {
            comment = "삭제된 댓글입니다.";
            memberNickName = "";
            isWriter = false;
        }

        return CommunityCommentResponse.builder()
                .sequence(entity.getSequence())
                .memberNickName(memberNickName)
                .communitySeq(entity.getCommunity().getSequence())
                .content(comment)
                .createdTime(DateChanger.format(entity.getCreatedTime()))
                .parentSeq(entity.getParentSeq())
                .isWriter(isWriter)
                .build();
    }
}
