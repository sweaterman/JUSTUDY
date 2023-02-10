package com.justudy.backend.category.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class CategoryServiceTest {

    private final CategoryEntity JAVA = new CategoryEntity("java", "Java", 1L, null);
    private final CategoryEntity SPRING = new CategoryEntity("spring", "Spring", 1L, null);
    private final CategoryEntity PYTHON = new CategoryEntity("python", "Python", 1L, null);
    private final CategoryEntity DATABASE = new CategoryEntity("database", "데이터베이스", 0L, null);
    private final CategoryEntity BACKEND = new CategoryEntity("backend", "백엔드", 0L, null);

    private CategoryRepository categoryRepository = mock(CategoryRepository.class);

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Transactional
    @Test
    @DisplayName("특정 부모의 서브 카테고리 가져오기")
    void getSubCategoryByParent() {
        //given
        final String PARENT = "backend";
        CategoryEntity backend = BACKEND;
        CategoryEntity java = JAVA;
        java.addParentCategory(BACKEND);
        CategoryEntity spring = SPRING;
        spring.addParentCategory(BACKEND);

        CategorySearch request = new CategorySearch(PARENT);

        BDDMockito.given(categoryRepository.findByParent(request))
                .willReturn(List.of(java, spring));

        //when
        List<CategoryResponse> subCategories = categoryService.getList(request);

        //then
        assertThat(subCategories.size()).isEqualTo(2);
        assertThat(subCategories.contains(new CategoryResponse(java))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(spring))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(backend))).isFalse();
    }

    @Test
    @DisplayName("전체 메인 카테고리 가져오기")
    void getMainCategories() {
        //given
        BDDMockito.given(categoryRepository.getAllParents())
                .willReturn(List.of(DATABASE, BACKEND));

        //when
        List<CategoryResponse> subCategories = categoryService.getMainCategories();

        //then
        assertThat(subCategories.size()).isEqualTo(2);
        assertThat(subCategories.contains(new CategoryResponse(JAVA))).isFalse();
        assertThat(subCategories.contains(new CategoryResponse(SPRING))).isFalse();
        assertThat(subCategories.contains(new CategoryResponse(DATABASE))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(BACKEND))).isTrue();

        BDDMockito.then(categoryRepository).should(times(1)).getAllParents();
    }

    @Test
    @DisplayName("전체 서브 카테고리 가져오기")
    void getSubCategories() {
        //given
        BDDMockito.given(categoryRepository.getAllChildren())
                .willReturn(List.of(JAVA, SPRING, PYTHON));

        //when
        List<CategoryResponse> subCategories = categoryService.getSubCategories();

        //then
        assertThat(subCategories.size()).isEqualTo(3);
        assertThat(subCategories.contains(new CategoryResponse(JAVA))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(SPRING))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(PYTHON))).isTrue();
        assertThat(subCategories.contains(new CategoryResponse(DATABASE))).isFalse();
        assertThat(subCategories.contains(new CategoryResponse(BACKEND))).isFalse();

        BDDMockito.then(categoryRepository).should(times(1)).getAllChildren();
    }
}