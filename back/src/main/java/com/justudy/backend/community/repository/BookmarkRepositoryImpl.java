package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.*;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.dto.response.BookmarkResponse;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final QBookmarkEntity qBookmark = QBookmarkEntity.bookmarkEntity;

    @Override
    public void saveBookmark(BookmarkCreate request) {
        queryFactory
                .insert(qBookmark)
                .columns(qBookmark.community.sequence,qBookmark.memberSeq,qBookmark.isChecked,qBookmark.createdTime)
                .values(request.getCommunity().getSequence(),request.getMemberSeq(),true, LocalDateTime.now())
                .execute();
    }

    @Override
    public BookmarkEntity readBookmark(BookmarkCreate request) {
        return queryFactory
                .selectFrom(qBookmark)
                .where(qBookmark.community.sequence.eq(request.getCommunity().getSequence()),qBookmark.memberSeq.eq(request.getMemberSeq()))
                .fetchFirst();
    }

    @Override
    public void updateBookmark(BookmarkCreate request) {
        queryFactory
                .update(qBookmark)
                .set(qBookmark.isChecked,true)
                .set(qBookmark.createdTime,LocalDateTime.now())
                .where(qBookmark.community.sequence.eq(request.getCommunity().getSequence()),qBookmark.memberSeq.eq(request.getMemberSeq()))
                .execute();
    }

    @Override
    public void deleteBookmark(Long id,Long userId) {
        queryFactory
                .update(qBookmark)
                .set(qBookmark.isChecked,false)
                .set(qBookmark.createdTime,LocalDateTime.now())
                .where(qBookmark.community.sequence.eq(id),qBookmark.memberSeq.eq(String.valueOf(userId)))
                .execute();

    }

    @Override
    public List<BookmarkEntity> readAllBookmarkByMember(Long userId) {
        return queryFactory
                .select(qBookmark)
                .from(qBookmark)
                .where(qBookmark.memberSeq.eq(String.valueOf(userId)))
                .fetch();
    }
}
