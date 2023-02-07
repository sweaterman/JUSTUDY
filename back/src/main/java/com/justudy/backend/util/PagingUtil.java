package com.justudy.backend.util;

import com.justudy.backend.study.domain.StudyEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Log4j2
public class PagingUtil {

    private final EntityManager entityManager;

    private Querydsl getQuerydsl(Class clazz) {
        PathBuilder<?> builder = new PathBuilderFactory().create(clazz);
        return new Querydsl(entityManager, builder);
    }

    public <T> PageImpl<T> getPageImpl(Pageable pageable, JPQLQuery<T> query, Class clazz) {    // 2)
        long totalCount = query.fetchCount();
        List<T> results = getQuerydsl(clazz).applyPagination(pageable, query).fetch();
        return new PageImpl<>(results, pageable, totalCount);
    }

    public <T> SliceImpl<T> getSliceImpl(Pageable pageable, JPQLQuery<T> query, Class clazz) {    // 2)
        List<T> results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl(results, pageable, hasNext);
    }
}
