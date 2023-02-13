package com.justudy.backend.study.dto.response.community;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudyCommunityCommentResponse {
    private Long sequence;
    private String memberNickName;
    private Long communitySeq;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private Boolean isDeleted;
    private Long parentSeq;

    private Boolean isWriter;

    public static StudyCommunityCommentResponse makeBuilder(StudyCommunityCommentEntity entity, Long loginSequence) {
        MemberEntity memberEntity = entity.getMember();
        String comment = entity.getContent();
        String memberNickName = memberEntity.getNickname();
        //댓글 작성자면 true
        boolean isWriter = loginSequence.longValue() == memberEntity.getSequence().longValue();

        if (entity.getIsDeleted()) {
            comment = "삭제된 댓글입니다.";
            memberNickName = "";
            isWriter = false;
        }
        return StudyCommunityCommentResponse.builder()
                .sequence(entity.getSequence())
                .memberNickName(memberNickName)
                .communitySeq(entity.getCommunity().getSequence())
                .content(comment)
                .createdTime(entity.getCreatedTime())
                .parentSeq(entity.getParentSeq())
                .isWriter(isWriter)
                .build();
    }
}
