package com.justudy.backend.member.domain;

import com.justudy.backend.category.CategoryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberCategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sequence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_seq")
    private CategoryEntity category;
}
