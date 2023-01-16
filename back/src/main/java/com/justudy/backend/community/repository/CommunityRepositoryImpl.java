package com.justudy.backend.community.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommunityRepositoryImpl implements  CommunityRepositorySupport{

    private final JPAQueryFactory queryFactory;
//    @Override
//    public List<CommunityEntity> getList() {
//        return null;
//    }
}
