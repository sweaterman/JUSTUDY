package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.*;
import com.justudy.backend.community.dto.request.CommunityBookmarkCreate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommunityBookmarkRepositoryImpl implements CommunityBookmarkRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final QCommunityBookmarkEntity qBookmark = QCommunityBookmarkEntity.communityBookmarkEntity;

    @Override
    public void saveBookmark(CommunityBookmarkCreate request) {
//        queryFactory
//                .insert(qBookmark)
//                .columns(qBookmark.community,qBookmark.memberSeq,qBookmark.isChecked,qBookmark.createdTime)
//                .values(request.getCommunity(),request.getMember(),true, LocalDateTime.now())
//                .execute();
    }

    @Override
    public Optional<CommunityBookmarkEntity> readBookmark(CommunityBookmarkCreate request) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qBookmark)
                .where(qBookmark.community.sequence.eq(request.getCommunity().getSequence()), qBookmark.member.sequence.eq(request.getMember().getSequence()))
                .fetchFirst());
    }

    @Override
    public void updateBookmark(CommunityBookmarkCreate request) {
        queryFactory
                .update(qBookmark)
                .set(qBookmark.isChecked,true)
                .set(qBookmark.createdTime,LocalDateTime.now())
                .where(qBookmark.community.sequence.eq(request.getCommunity().getSequence()),qBookmark.member.sequence.eq(request.getMember().getSequence()))
                .execute();
    }

    @Override
    public void deleteBookmark(Long id,Long userId) {
        queryFactory
                .update(qBookmark)
                .set(qBookmark.isChecked,false)
                .set(qBookmark.createdTime,LocalDateTime.now())
                .where(qBookmark.community.sequence.eq(id),qBookmark.member.sequence.eq(userId))
                .execute();

    }

    @Override
    public List<CommunityBookmarkEntity> readAllBookmarkByMember(Long userId) {
        return queryFactory
                .select(qBookmark)
                .from(qBookmark)
                .where(qBookmark.member.sequence.eq(userId),qBookmark.isChecked.eq(true))
                .fetch();
    }
}
