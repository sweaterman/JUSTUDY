package com.justudy.backend.category.service;

import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.category.repository.CategoryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<String> getList(CategorySearch categorySearch) {
        return categoryRepository.findByParent(categorySearch);
    }

    public List<String> getMainCategories() {
        return categoryRepository.getAllParentsName();
    }

    public List<String> getSubCategories() {
        return categoryRepository.getAllChildrenName();
    }
}
