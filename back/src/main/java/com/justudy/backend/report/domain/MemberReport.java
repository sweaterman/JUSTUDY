package com.justudy.backend.report.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("member")
@Entity
public class MemberReport extends Report {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity targetMember;

    public MemberReport(MemberEntity reporter, String content,
                        MemberEntity targetMember) {
        super(reporter, targetMember.getSequence(), content);
        this.targetMember = targetMember;
    }
}
