package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.category.exception.CategoryNotFound;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.justudy.backend.category.domain.QCategoryEntity.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final Long ALGORITHM_SEQUENCE = 5L;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CategoryEntity> findByKey(String key) {
        return Optional.ofNullable(queryFactory
                .selectFrom(categoryEntity)
                .where(keyEq(key))
                .fetchOne());
    }

    @Override
    public Optional<CategoryEntity> findByValue(String value) {
        return Optional.ofNullable(queryFactory
                .selectFrom(categoryEntity)
                .where(valueEq(value))
                .fetchOne());
    }

    @Override
    public List<CategoryEntity> findByParent(CategorySearch condition) {
        return queryFactory
                .select(categoryEntity)
                .from(categoryEntity)
                .where(parentEq(condition.getMain()))
                .where(categoryEntity.parentCategory.isNotNull())
                .fetch();
    }

    //Todo 이게 굳이 필요한가? CategorySearch가 없으면 되는거 아닌가요?
    //Todo fetchJoin 필요한가요???
    @Override
    public List<CategoryEntity> getAllChildren() {
        return queryFactory
                .selectFrom(categoryEntity)
                .where(categoryEntity.parentCategory.isNotNull())
                .fetch();
    }

    @Override
    public List<CategoryEntity> getAllParents() {
        return queryFactory
                .selectFrom(categoryEntity)
                .where(categoryEntity.parentCategory.isNull())
                .fetch();
    }

    @Override
    public List<CategoryEntity> getMemberCategories() {
        return queryFactory
                .selectFrom(categoryEntity)
                .where(categoryEntity.parentCategory.isNotNull(),
                        categoryEntity.parentCategory.sequence.eq(ALGORITHM_SEQUENCE).not())
                .fetch();
    }

    private BooleanExpression keyEq(String key) {
        return key != null ? categoryEntity.key.eq(key) : null;
    }

    private BooleanExpression valueEq(String value) {
        return value != null ? categoryEntity.value.eq(value) : null;
    }

    private BooleanExpression parentEq(String mainKey) {
        if (mainKey != null) {
            CategoryEntity parent = findByKey(mainKey)
                    .orElseThrow(CategoryNotFound::new);
            return categoryEntity.parentCategory.eq(parent);
        }
        return null;
    }
}
