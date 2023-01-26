package com.justudy.backend.study.repository;

import com.justudy.backend.util.PagingUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
}
