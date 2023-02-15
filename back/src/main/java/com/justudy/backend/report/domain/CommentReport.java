package com.justudy.backend.report.domain;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("comment")
@Entity
public class CommentReport extends Report {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_seq")
    private CommunityCommentEntity targetComment;

    public CommentReport(MemberEntity reporter, String content,
                         CommunityCommentEntity targetComment) {
        super(reporter, targetComment.getSequence(), content);
        this.targetComment = targetComment;
    }
}
