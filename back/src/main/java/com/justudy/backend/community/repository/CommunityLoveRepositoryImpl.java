package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.domain.QCommunityLoveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommunityLoveRepositoryImpl implements CommunityLoveRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QCommunityLoveEntity qLove = QCommunityLoveEntity.communityLoveEntity;

    @Override
    public Optional<CommunityLoveEntity> findLove(Long loginSequence, Long communitySequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qLove)
                .where(qLove.memberSequence.eq(loginSequence), qLove.communitySequence.eq(communitySequence))
                .fetchFirst());
    }

    @Override
    public void deleteAllByCommunity(Long communitySequence) {
        queryFactory
                .delete(qLove)
                .where(qLove.communitySequence.eq(communitySequence))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public Integer countOfLove(Long communitySequence) {
        return Math.toIntExact(queryFactory
                .select(qLove.count())
                .from(qLove)
                .where(qLove.communitySequence.eq(communitySequence))
                .fetchFirst());
    }

    @Override
    public List<Long> findCommunitySequence(Long loginSequence) {
        return queryFactory.select(qLove.communitySequence)
                .from(qLove)
                .where(qLove.memberSequence.eq(loginSequence))
                .fetch();
    }
}
