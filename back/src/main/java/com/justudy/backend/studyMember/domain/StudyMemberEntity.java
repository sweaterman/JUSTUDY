package com.justudy.backend.studyMember.domain;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_member")
@Entity
public class StudyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_member_seq")
    private Long sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_seq")
    private StudyEntity study;
    @Column(name = "study_member_alarm", columnDefinition = "TINYINT(1)")
    private Boolean alarm;
    @Column(name = "study_member_king")
    private Integer king;
    @Column(name = "study_member_created_time")
    private LocalDateTime createdTime;

    public void changeKing(Integer king) {
        this.king = king;
    }
}
