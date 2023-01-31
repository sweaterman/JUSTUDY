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

    @Override
    public Optional<String> findUserId(String userId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.userId)
                .from(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .fetchOne()
        );
    }

    @Override
    public Optional<String> findNickname(String nickname) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.nickname)
                .from(memberEntity)
                .where(memberEntity.nickname.eq(nickname))
                .fetchOne()
        );
    }

    @Override
    public Optional<String> findSsafyId(String ssafyId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.ssafyId)
                .from(memberEntity)
                .where(memberEntity.ssafyId.eq(ssafyId))
                .fetchOne()
        );
    }
}
