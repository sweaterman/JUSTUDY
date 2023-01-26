package com.justudy.backend.member.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<Tuple> findPasswordByUserId(String userId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.sequence, memberEntity.password)
                .from(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .fetchOne());
    }
}
