package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.*;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.dto.request.LoveCreate;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class LoveRepositoryImpl implements LoveRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final QLoveEntity qLove = QLoveEntity.loveEntity;

    @Override
    public Long saveLove(LoveCreate request) {
        return queryFactory
                .insert(qLove)
                .columns(qLove.community, qLove.isChecked)
                .values(CommunityEntity.builder().sequence(request.getCommunity().getSequence()).member_seq(request.getCommunity().getMember_seq()).build(), true)
                .execute();
    }

    @Override
    public Long updateLove(LoveCreate request, boolean flag) {
        return queryFactory
                .update(qLove)
                .where(qLove.community.sequence.eq(request.getCommunity().getSequence()), qLove.community.member_seq.eq(request.getCommunity().getMember_seq()))
                .set(qLove.isChecked, flag)
                .execute();
    }

    @Override
    public List<LoveEntity> readAllLoveByCommunity(Long id) {
        return queryFactory
                .selectFrom(qLove)
                .where(qLove.community.sequence.eq(id), qLove.isChecked.eq(true))
                .fetch();
    }

    @Override
    public Long deleteAllLoveByCommunity(Long id) {
        return queryFactory
                .delete(qLove)
                .where(qLove.community.sequence.eq(id))
                .execute();
    }

    @Override
    public Boolean readLove(LoveCreate request) {
        return queryFactory
                .select(qLove.isChecked)
                .from(qLove)
                .where(qLove.community.sequence.eq(request.getCommunity().getSequence()), qLove.community.member_seq.eq(request.getCommunity().getMember_seq()))
                .fetchFirst();
    }

    @Override
    public Integer readLoveCountByCommunity(Long communitySequence) {
        queryFactory
                .select(qLove.isChecked.count())
                .from(qLove)
                .where(qLove.community.sequence.eq(communitySequence), qLove.isChecked.eq(true))
                .fetchFirst();
        return null;
    }

}
