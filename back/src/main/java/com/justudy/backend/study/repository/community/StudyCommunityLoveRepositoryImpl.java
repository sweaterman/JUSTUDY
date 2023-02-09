package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.QStudyCommunityLoveEntity;
import com.justudy.backend.study.domain.community.StudyCommunityLoveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudyCommunityLoveRepositoryImpl implements StudyCommunityLoveRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QStudyCommunityLoveEntity qLove = QStudyCommunityLoveEntity.studyCommunityLoveEntity;

    @Override
    public Optional<StudyCommunityLoveEntity> findLove(Long loginSequence, Long communitySequence) {
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
