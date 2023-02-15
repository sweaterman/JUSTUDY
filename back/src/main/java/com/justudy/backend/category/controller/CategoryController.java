package com.justudy.backend.category.controller;

import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories(@ModelAttribute CategorySearch  categorySearch) {
        return categoryService.getList(categorySearch);
    }

    @GetMapping("/main-category")
    public List<CategoryResponse> getMainCategories() {
        return categoryService.getMainCategories();
    }

    @GetMapping("/sub-category")
    public List<CategoryResponse> getSubCategories() {
        return categoryService.getSubCategories();
    }

    @GetMapping("/member-category")
    public List<CategoryResponse> getMemberCategories() {
        return categoryService.getMemberCategories();
    }
}
