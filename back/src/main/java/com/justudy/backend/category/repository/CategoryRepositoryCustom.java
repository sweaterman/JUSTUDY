package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    Optional<CategoryEntity> findByKey(String key);

    Optional<CategoryEntity> findByValue(String value);

    List<CategoryEntity> findByParent(CategorySearch condition);

    List<CategoryEntity> getAllChildren();

    List<CategoryEntity> getAllParents();

    List<CategoryEntity> getMemberCategories();
}
