package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.domain.QCommunityBookmarkEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CommunityBookmarkRepositoryImpl implements CommunityBookmarkRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final QCommunityBookmarkEntity qBookmark = QCommunityBookmarkEntity.communityBookmarkEntity;

    @Override
    public Optional<CommunityBookmarkEntity> findBookmark(Long loginSequence, Long communitySequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qBookmark)
                .where(qBookmark.memberSequence.eq(loginSequence), qBookmark.communitySequence.eq(communitySequence))
                .fetchFirst());
    }
}
