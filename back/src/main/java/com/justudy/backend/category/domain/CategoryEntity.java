package com.justudy.backend.category.domain;

import com.justudy.backend.file.domain.UploadFileEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_seq")
    private Long sequence;

    @Column(name = "category_key")
    private String key;

    @Column(name = "category_value")
    private String value;

    @Column(name = "category_level")
    private Long categoryLevel;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "image_seq")
    UploadFileEntity imageFile;

    /** 셀프 양방향 연관 관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_seq")
    CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> children = new ArrayList<>();

    @Builder
    public CategoryEntity(String key, String value,Long categoryLevel, UploadFileEntity imageFile) {
        this.key = key;
        this.value = value;
        this.categoryLevel = categoryLevel;
        this.imageFile = imageFile;
    }

    /** 연관관계 편의 메소드 */
    public void addImage(UploadFileEntity imageFile) {
        this.imageFile = imageFile;
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
