package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepositoryCustom {
}
