package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.exception.InvalidRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.justudy.backend.category.domain.QCategoryEntity.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CategoryEntity> findByKey(String key) {
        return Optional.ofNullable(queryFactory.selectFrom(categoryEntity)
                .where(keyEq(key))
                .fetchOne());
    }

    @Override
    public List<String> findByParent(CategorySearch condition) {
        return queryFactory.select(categoryEntity.key)
                .from(categoryEntity)
                .where(parentEq(condition.getMain()))
                .fetch();
    }

    @Override
    public List<String> getAllChildrenName() {
        return queryFactory.select(categoryEntity.key)
                .from(categoryEntity)
                .where(categoryEntity.parentCategory.isNotNull())
                .fetch();
    }

    @Override
    public List<String> getAllParentsName() {
        return queryFactory.select(categoryEntity.key)
                .from(categoryEntity)
                .where(categoryEntity.parentCategory.isNull())
                .fetch();
    }

    private BooleanExpression keyEq(String key) {
        return key != null ? categoryEntity.key.eq(key) : null;
    }

    private BooleanExpression parentEq(String mainName) {
        if (mainName != null) {
            CategoryEntity parent = findByKey(mainName)
                    .orElseThrow(InvalidRequest::new);
            return categoryEntity.parentCategory.eq(parent);
        }
        return null;
    }
}
