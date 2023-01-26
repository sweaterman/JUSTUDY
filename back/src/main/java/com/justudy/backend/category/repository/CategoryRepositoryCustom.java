package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    Optional<CategoryEntity> findByName(String name);

    List<String> findByParent(CategorySearch condition);

    List<String> getAllChildrenName();

    List<String> getAllParentsName();
}
