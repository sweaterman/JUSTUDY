package com.justudy.backend.category.service;

import com.justudy.backend.category.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class CategoryServiceTest {

    private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    @DisplayName("서브 카테고리 가져오기")
    void getSubCategories() {
        //given
        BDDMockito.given(categoryRepository.getAllChildrenName())
                .willReturn(List.of("Java", "Spring", "Python"));

        //when
        List<String> subCategories = categoryService.getSubCategories();

        //then
        assertThat(subCategories.size()).isEqualTo(3);
        assertThat(subCategories.contains("Java")).isTrue();
        assertThat(subCategories.contains("Spring")).isTrue();
        assertThat(subCategories.contains("Python")).isTrue();
        assertThat(subCategories.contains("Database")).isFalse();
    }
}