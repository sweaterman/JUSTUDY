package com.justudy.backend.member.domain;

import com.justudy.backend.category.domain.CategoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_category")
@Entity
public class MemberCategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_category_seq")
    private Long sequence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_seq")
    private CategoryEntity category;

    //==연관관계 편의메소드==//
    public void addMember(MemberEntity member) {
        this.member = member;
    }

    //생성 메소드를 위한 메소드
    public void changeCategory(CategoryEntity category) {
        this.category = category;
    }

    //==생성메소드==//
    public static MemberCategoryEntity createMemberCategory(CategoryEntity category) {
        MemberCategoryEntity memberCategory = new MemberCategoryEntity();
        memberCategory.changeCategory(category);

        return memberCategory;
    }

}
