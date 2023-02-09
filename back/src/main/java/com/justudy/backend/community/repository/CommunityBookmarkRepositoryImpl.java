package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.domain.QCommunityBookmarkEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommunityBookmarkRepositoryImpl implements CommunityBookmarkRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QCommunityBookmarkEntity qBookmark = QCommunityBookmarkEntity.communityBookmarkEntity;

    @Override
    public Optional<CommunityBookmarkEntity> findBookmark(Long loginSequence, Long communitySequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qBookmark)
                .where(qBookmark.memberSequence.eq(loginSequence),
                        qBookmark.communitySequence.eq(communitySequence))
                .fetchFirst());
    }

    @Override
    public void deleteAllByCommunity(Long communitySequence) {
        queryFactory
                .delete(qBookmark)
                .where(qBookmark.communitySequence.eq(communitySequence))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public List<Long> findCommunitySequence(Long loginSequence) {
        return queryFactory.select(qBookmark.communitySequence)
                .from(qBookmark)
                .where(qBookmark.memberSequence.eq(loginSequence))
                .fetch();
    }
}
