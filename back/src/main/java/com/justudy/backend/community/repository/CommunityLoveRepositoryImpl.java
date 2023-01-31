package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.*;
import com.justudy.backend.community.dto.request.CommunityLoveCreate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommunityLoveRepositoryImpl implements CommunityLoveRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final QCommunityLoveEntity qLove = QCommunityLoveEntity.communityLoveEntity;

    @Override
    public Long saveLove(CommunityLoveCreate request) {
        return null;
    }

    @Override
    public Long updateLove(CommunityLoveCreate request, boolean flag) {
        return queryFactory
                .update(qLove)
                .where(qLove.community.sequence.eq(request.getCommunity().getSequence()), qLove.community.member.sequence.eq(request.getCommunity().getMember().getSequence()))
                .set(qLove.isChecked, flag)
                .execute();
    }

    @Override
    public List<CommunityLoveEntity> readAllLoveByCommunity(Long id) {
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
    public Optional<CommunityLoveEntity> readLove(CommunityLoveCreate request) {
        return Optional.ofNullable(queryFactory
                .select(qLove)
                .from(qLove)
                .where(qLove.community.sequence.eq(request.getCommunity().getSequence()), qLove.member.sequence.eq(request.getMember().getSequence()))
                .fetchFirst());
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
