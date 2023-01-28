package com.justudy.backend.category.controller;

import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/category")
    public List<String> getCategories(@ModelAttribute CategorySearch  categorySearch) {
        return categoryService.getList(categorySearch);
    }

    @GetMapping("/main-category")
    public List<String> getMainCategories() {
        return categoryService.getMainCategories();
    }

    @GetMapping("/sub-category")
    public List<String> getSubCategories() {
        return categoryService.getSubCategories();
    }
}
