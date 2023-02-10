package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.QStudyCommunityBookmarkEntity;
import com.justudy.backend.study.domain.community.StudyCommunityBookmarkEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudyCommunityBookmarkRepositoryImpl implements StudyCommunityBookmarkRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final QStudyCommunityBookmarkEntity qBookmark = QStudyCommunityBookmarkEntity.studyCommunityBookmarkEntity;

    @Override
    public Optional<StudyCommunityBookmarkEntity> findBookmark(Long loginSequence, Long communitySequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qBookmark)
                .where(qBookmark.memberSequence.eq(loginSequence), qBookmark.communitySequence.eq(communitySequence))
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
