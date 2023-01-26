package com.justudy.backend.category.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_seq")
    private Long sequence;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_level")
    private Long categoryLevel;


    /** 셀프 양방향 연관 관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> children = new ArrayList<>();

    @Builder
    public CategoryEntity(String name,Long categoryLevel) {
        this.name = name;
        this.categoryLevel = categoryLevel;
    }

    /** 셀프 양방향 연관관계 편의 메소드 */
    public void addParentCategory(CategoryEntity parent) {
        this.parentCategory = parent;
        parent.addChildCategory(this);
    }

    private void addChildCategory(CategoryEntity child) {
        this.children.add(child);
    }
}
