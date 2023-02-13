package com.justudy.backend.admin.repository;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.request.MemberSearchType;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.request.SearchType;
import com.justudy.backend.member.domain.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.justudy.backend.community.domain.QCommunityEntity.communityEntity;
import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@RequiredArgsConstructor
@Repository
public class AdminRepository {

    private final JPAQueryFactory queryFactory;

    public Long getCountOfMembers() {
        return queryFactory.select(memberEntity.count())
                .from(memberEntity)
                .fetchOne();
    }

    public List<MemberEntity> getMembers(MemberSearch memberSearch) {
        return queryFactory.selectFrom(memberEntity)
                .where(eqMemberTypeAndSearch(memberSearch))
                .limit(memberSearch.getSize())
                .offset(memberSearch.getOffset())
                .orderBy(memberEntity.modifiedTime.desc())
                .fetch();
    }
    public Long getCountMemberBySearch(MemberSearch memberSearch) {
        return queryFactory.select(memberEntity.count())
                .from(memberEntity)
                .where(eqMemberTypeAndSearch(memberSearch))
                .fetchOne();
    }

    public List<CommunityEntity> getCommunities(CommunitySearch communitySearch) {
        return queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(eqCommunityTypeAndSearch(communitySearch))
                .limit(communitySearch.getSize())
                .offset(communitySearch.getOffset())
                .fetch();
    }
    public Long getCountCommunitiesBySearch(CommunitySearch communitySearch) {
        return queryFactory.select(communityEntity.count())
                .from(communityEntity)
                .where(eqCommunityTypeAndSearch(communitySearch))
                .fetchFirst();
    }

    public Long countSignupByTime(LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.select(memberEntity.count())
                .from(memberEntity)
                .where(memberEntity.createdTime.between(startDate, endDate))
                .fetchOne();
    }

    public Long countCommunityByTime(LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.createdTime.between(startDate, endDate))
                .fetchOne();
    }

    // todo 리팩토링 해야함.....
    private BooleanExpression eqCommunityTypeAndSearch(CommunitySearch communitySearch) {
        SearchType type = communitySearch.getType();
        if (type == null) {
            return null;
        }
        if (!StringUtils.hasText(communitySearch.getSearch())) {
            return null;
        }
        return type.getExpression(communitySearch.getSearch());
    }

    private BooleanExpression eqMemberTypeAndSearch(MemberSearch memberSearch) {
        MemberSearchType type = memberSearch.getType();
        if (type == null) {
            return null;
        }
        if (!StringUtils.hasText(memberSearch.getSearch())) {
            return null;
        }
        return type.getExpression(memberSearch.getSearch());
    }
}
