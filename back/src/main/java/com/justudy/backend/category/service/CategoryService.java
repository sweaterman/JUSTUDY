package com.justudy.backend.category.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.exception.CategoryNotFound;
import com.justudy.backend.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getList(CategorySearch categorySearch) {
        return categoryRepository.findByParent(categorySearch).stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public CategoryEntity getCategoryEntityByKey(String categoryKey) {
        return categoryRepository.findByKey(categoryKey)
                .orElseThrow(CategoryNotFound::new);
    }

    public List<CategoryResponse> getMainCategories() {
        return categoryRepository.getAllParents().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getSubCategories() {
        return categoryRepository.getAllChildren().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getMemberCategories() {
        return categoryRepository.getMemberCategories().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }
}
