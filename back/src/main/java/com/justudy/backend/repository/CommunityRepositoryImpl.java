package com.justudy.backend.repository;

import com.justudy.backend.domain.CommunityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements  CommunityRepositorySupport{

    private final JPAQueryFactory queryFactory;
//    @Override
//    public List<CommunityEntity> getList() {
//        return null;
//    }
}
