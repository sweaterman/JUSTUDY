package com.justudy.backend.category.controller;

import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    private final String COMMON_URL = "/api/category";

    final CategoryResponse BACKEND = new CategoryResponse("backend", "백엔드");
    final CategoryResponse FRONTEND = new CategoryResponse("frontend", "프론트엔드");
    final CategoryResponse CS = new CategoryResponse("computer-science", "CS");
    final CategoryResponse JAVA = new CategoryResponse("java", "Java");
    final CategoryResponse SPRING = new CategoryResponse("spring", "Spring");
    final CategoryResponse PYTHON = new CategoryResponse("python", "Python");
    final CategoryResponse VUE = new CategoryResponse("vue", "Vue");
    final CategoryResponse REACT = new CategoryResponse("react", "React");



    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("특정 카테고리 반환")
    void getSubCategoriesByParent() throws Exception {
        //given
        CategorySearch searchBack = new CategorySearch(BACKEND.getKey());
        CategorySearch searchFront = new CategorySearch(FRONTEND.getKey());

        BDDMockito.given(categoryService.getList(searchBack))
                .willReturn(List.of(JAVA, SPRING, PYTHON));
        BDDMockito.given(categoryService.getList(searchFront))
                .willReturn(List.of(JAVA, SPRING, PYTHON));
        //expected
        mockMvc.perform(get(COMMON_URL + "?main=" + BACKEND.getKey())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].key").value(JAVA.getKey()))
                .andExpect(jsonPath("$[0].value").value(JAVA.getValue()))
                .andExpect(jsonPath("$[1].key").value(SPRING.getKey()))
                .andExpect(jsonPath("$[1].value").value(SPRING.getValue()))
                .andExpect(jsonPath("$[2].key").value(PYTHON.getKey()))
                .andExpect(jsonPath("$[2].value").value(PYTHON.getValue()))
                .andDo(print());

        BDDMockito.then(categoryService).should(only()).getList(searchBack);
    }

    @Test
    @DisplayName("메인카테고리 반환")
    void getMainCategories() throws Exception {
        //given
        BDDMockito.given(categoryService.getMainCategories())
                .willReturn(List.of(BACKEND, FRONTEND, CS));

        //expected
        mockMvc.perform(get(COMMON_URL + "/main-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].key").value(BACKEND.getKey()))
                .andExpect(jsonPath("$[0].value").value(BACKEND.getValue()))
                .andExpect(jsonPath("$[1].key").value(FRONTEND.getKey()))
                .andExpect(jsonPath("$[1].value").value(FRONTEND.getValue()))
                .andExpect(jsonPath("$[2].key").value(CS.getKey()))
                .andExpect(jsonPath("$[2].value").value(CS.getValue()))
                .andDo(print());

        BDDMockito.then(categoryService).should(times(1)).getMainCategories();
    }

    @Test
    @DisplayName("서브카테고리 반환")
    void getSubCategories() throws Exception {
        //given
        BDDMockito.given(categoryService.getSubCategories())
                .willReturn(List.of(JAVA, SPRING, PYTHON));

        //expected
        mockMvc.perform(get(COMMON_URL + "/sub-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].key").value(JAVA.getKey()))
                .andExpect(jsonPath("$[0].value").value(JAVA.getValue()))
                .andExpect(jsonPath("$[1].key").value(SPRING.getKey()))
                .andExpect(jsonPath("$[1].value").value(SPRING.getValue()))
                .andExpect(jsonPath("$[2].key").value(PYTHON.getKey()))
                .andExpect(jsonPath("$[2].value").value(PYTHON.getValue()))
                .andDo(print());

        BDDMockito.then(categoryService).should(times(1)).getSubCategories();
    }

}