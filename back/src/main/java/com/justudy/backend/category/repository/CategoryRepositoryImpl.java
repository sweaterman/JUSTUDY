package com.justudy.backend.category.repository;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategorySearch;
import com.justudy.backend.member.exception.InvalidRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.justudy.backend.category.domain.QCategoryEntity.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CategoryEntity> findByName(String name) {
        return Optional.ofNullable(queryFactory.selectFrom(categoryEntity)
                .where(nameEq(name))
                .fetchOne());
    }

    @Override
    public List<String> findByParent(CategorySearch condition) {
        return queryFactory.select(categoryEntity.name)
                .from(categoryEntity)
                .where(parentEq(condition.getMain()))
                .fetch();
    }

    @Override
    public List<String> getAllChildrenName() {
        return queryFactory.select(categoryEntity.name)
                .from(categoryEntity)
                .where(categoryEntity.parentCategory.isNotNull())
                .fetch();
    }

    @Override
    public List<String> getAllParentsName() {
        return queryFactory.select(categoryEntity.name)
                .from(categoryEntity)
                .where(categoryEntity.parentCategory.isNull())
                .fetch();
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? categoryEntity.name.eq(name) : null;
    }

    private BooleanExpression parentEq(String mainName) {
        if (mainName != null) {
            CategoryEntity parent = findByName(mainName)
                    .orElseThrow(InvalidRequest::new);
            return categoryEntity.parentCategory.eq(parent);
        }
        return null;
    }
}
