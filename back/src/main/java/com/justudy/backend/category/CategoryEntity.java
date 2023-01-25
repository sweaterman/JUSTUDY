package com.justudy.backend.category;

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
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_seq")
    private Long sequence;

    @Column(name = "category_name")
    private String name;

    /** 셀프 양방향 연관 관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> children = new ArrayList<>();

    @Builder
    public CategoryEntity(String name, CategoryEntity parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    /** 셀프 양방향 연관관계 편의 메소드 */
    public void addParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
        parentCategory.getChildren().add(this);
    }
}
