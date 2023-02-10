package com.justudy.backend.study.domain.community;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_community_comment")
@Entity
public class StudyCommunityCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_comment_seq")
    private Long sequence;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_seq")
    private StudyCommunityEntity community;
    @Column(name = "study_comment_content")
    private String content;
    @Column(name = "study_comment_created_time")
    private LocalDateTime createdTime;
    @Column(name = "study_comment_modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "study_comment_is_deleted", columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;
    @Column(name = "study_comment_group")
    private Integer group;
    @Column(name = "study_comment_order")
    private Integer order;
    @Column(name = "study_comment_parent_seq")
    private Long parentSeq;
    @Column(name = "study_comment_step")
    private Integer step;
    @Column(name = "study_comment_child_number")
    private Integer childNumber;

    public void changeIsDeleted(boolean value) {
        this.isDeleted = value;
    }

    public void update(String content) {
        this.content=content;
        this.modifiedTime=LocalDateTime.now();
    }
}
