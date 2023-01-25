package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("parent name으로 조회")
    void findByParent() {
        //given
        final String MAIN_CATEGORY_NAME = "backend";
        CategorySearch condition = new CategorySearch(MAIN_CATEGORY_NAME);

        //when
        List<String> list = categoryRepository.findByParent(condition);

        //then
        assertThat(list.contains("Java")).isTrue();
        assertThat(list.contains("Spring")).isTrue();
    }

    @Test
    @DisplayName("name으로 조회")
    void findByName() {
        //given
        final String JAVA_SCRIPT = "JavaScript";

        //when
        CategoryEntity category = categoryRepository.findByName(JAVA_SCRIPT).get();

        //then
        Assertions.assertThat(category.getName()).isEqualTo(JAVA_SCRIPT);
        Assertions.assertThat(category.getCategoryLevel()).isEqualTo(1L);
    }
}