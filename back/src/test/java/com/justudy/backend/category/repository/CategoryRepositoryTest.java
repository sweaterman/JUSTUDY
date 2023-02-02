//package com.justudy.backend.category.repository;
//
//import com.justudy.backend.category.domain.CategoryEntity;
//import com.justudy.backend.category.dto.request.CategorySearch;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@SpringBootTest
//class CategoryRepositoryTest {
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Test
//    @DisplayName("parent name으로 조회")
//    void findByParent() {
//        //given
//        final String MAIN_CATEGORY_NAME = "backend";
//        CategorySearch condition = new CategorySearch(MAIN_CATEGORY_NAME);
//
//        //when
//        List<CategoryEntity> list = categoryRepository.findByParent(condition);
//
//        //then
//        assertThat(list.contains("Java")).isTrue();
//        assertThat(list.contains("Spring")).isTrue();
//    }
//
//    @Test
//    @DisplayName("name으로 조회")
//    void findByName() {
//        //given
//        final String JAVA_SCRIPT = "JavaScript";
//
//        //when
//        CategoryEntity category = categoryRepository.findByKey(JAVA_SCRIPT).get();
//
//        //then
//        assertThat(category.getKey()).isEqualTo(JAVA_SCRIPT);
//        assertThat(category.getCategoryLevel()).isEqualTo(1L);
//    }
//
//    @Test
//    @DisplayName("sub-category조회")
//    void getAllSubCategory() {
//
//        List<String> category = categoryRepository.getAllChildrenName();
//        System.out.println(">>>>>>>>>>>>>>>>>" + category);
//    }
//}