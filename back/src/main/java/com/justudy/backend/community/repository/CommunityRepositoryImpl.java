package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.QCommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.request.SearchOrderType;
import com.justudy.backend.community.dto.request.SearchType;
import com.justudy.backend.community.exception.ImportBoardFail;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.justudy.backend.category.domain.QCategoryEntity.categoryEntity;
import static com.justudy.backend.community.domain.QCommunityEntity.communityEntity;
import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@Slf4j
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QCommunityEntity qCommunity = communityEntity;
    private final long MAX_POPULAR_SIZE = 20;
    private final EntityManager em;


    @Override
    public List<CommunityEntity> getAllList(CommunitySearch communitySearch) {
        List<CommunityEntity> list = new ArrayList<>();

        List<CommunityEntity> notices = queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(true),
                        communityEntity.isDeleted.eq(false))
                .limit(communitySearch.getNoticeBoardSize())
                .orderBy(communityEntity.sequence.desc())
                .fetch();

        List<CommunityEntity> commonList = queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .join(communityEntity.category, categoryEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(false),
                        communityEntity.isDeleted.eq(false),
                        eqCategory(communitySearch.getCategory()),
                        eqTypeAndSearch(communitySearch))
                .limit(communitySearch.getSize() - notices.size())
                .offset(communitySearch.getOffsetWithNotice(notices.size()))
                .orderBy(orderByCondition(communitySearch))
                .fetch();
        if (commonList.isEmpty()) {
            return List.of();
        }
        list.addAll(notices);
        list.addAll(commonList);
        return list;
    }

    @Override
    public Long getCountOfList(CommunitySearch communitySearch) {
        return queryFactory.select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.isHighlighted.eq(false),
                        communityEntity.isDeleted.eq(false),
                        eqCategory(communitySearch.getCategory()),
                        eqTypeAndSearch(communitySearch))
                .fetchFirst();
    }

    @Override
    public List<CommunityEntity> getAllNotice(Pageable pageable) {
        return queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(true),
                        communityEntity.isDeleted.eq(false))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(communityEntity.sequence.desc())
                .fetch();
    }

    @Override
    public Long getCountOfNotices() {
        return queryFactory.select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.isHighlighted.eq(true),
                        communityEntity.isDeleted.eq(false))
                .fetchFirst();
    }

    public List<CommunityEntity> getMostLoveListOfWeek(Pageable pageable) {
        return queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(false),
                        communityEntity.isDeleted.eq(false))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(communityEntity.weekLoveCount.desc())
                .fetch();
    }

    @Override
    public Optional<CommunityEntity> findBySequence(Long sequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .join(communityEntity.category, categoryEntity).fetchJoin()
                .where(communityEntity.sequence.eq(sequence),
                        communityEntity.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public List<CommunityEntity> getListBySequences(List<Long> sequences) {
        return queryFactory
                .selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(communityEntity.sequence.in(sequences),
                        communityEntity.isDeleted.eq(false))
                .fetch();
    }

    @Override
    public void updateWeekLoveCount() {
        queryFactory
                .update(communityEntity)
                .set(communityEntity.loveCount, communityEntity.loveCount.add(communityEntity.weekLoveCount))
                .execute();

        queryFactory
                .update(communityEntity)
                .set(communityEntity.weekLoveCount, 0)
                .execute();

        em.clear();
    }





    @Override
    public Page<CommunityEntity> findAllByNotice(Pageable pageable) {
        JPQLQuery<CommunityEntity> query = queryFactory
                .selectFrom(qCommunity)
                .where(qCommunity.isHighlighted.eq(true))
                .orderBy(qCommunity.createdTime.desc());
        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }

    @Override
    public Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
//        if (!(name == null || name.isEmpty())) {
//            JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(qCommunity)
////                    .join(qCommunity.member_seq, member)
//                    .where(qCommunity.member.nickname.containsIgnoreCase(name));
//            return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
//        }
        //name이 없을 경우 title과 content 탐색
        JPQLQuery<CommunityEntity> query = queryFactory
                .selectFrom(qCommunity)
//                .join(community.member_seq, member)
                .where(eqNickname(name), eqTitle(title), eqContent(content));
        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }

    @Override
    public Page<CommunityEntity> findAll(Pageable pageable, String category) {

        return null;
//        JPQLQuery<CommunityEntity> query = queryFactory
//                .selectFrom(qCommunity)
//                .where(qCommunity.category_seq.eq(Long.valueOf(category)));
//
//        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }

    @Override
    public Long noticeCount() {
        return queryFactory
                .select(qCommunity.count())
                .from(qCommunity)
                .where(qCommunity.isHighlighted.eq(true))
                .fetchFirst();
    }

    @Override
    public List<CommunityEntity> findPopularCommunity() {
        return queryFactory
                .selectFrom(qCommunity)
                .orderBy(qCommunity.weekLoveCount.desc())
                .limit(MAX_POPULAR_SIZE)
                .fetch();
    }

    private BooleanExpression eqTypeAndSearch(CommunitySearch communitySearch) {
        SearchType type = communitySearch.getType();
        log.info("type = {}", type);
        if (type == null) {
            return null;
        }
        log.info("communitySearch.getSearch() = {}", communitySearch.getSearch());
        if (communitySearch.getSearch() == null) {
            return null;
        }
        log.info("type.getExpression = {}", type.getExpression(communitySearch.getSearch()));
        return type.getExpression(communitySearch.getSearch());
    }

    private OrderSpecifier<?> orderByCondition(CommunitySearch communitySearch) {
        SearchOrderType orderType = communitySearch.getOrder();
        if (orderType == null) {
            return communityEntity.sequence.desc();
        }
        return orderType.getOrderSpecifier();
    }

    private BooleanExpression eqCategory(String category) {
        if (category == null || category.isEmpty()) {
            return null;
        }
        return communityEntity.category.key.eq(category);
    }


    private BooleanExpression eqNickname(String name) {
        QCommunityEntity entity = communityEntity;
        if (name == null || name.isEmpty()) {
            return null;
        }
        return entity.member.nickname.containsIgnoreCase(name);
    }

    private BooleanExpression eqTitle(String title) {
        QCommunityEntity entity = communityEntity;
        if (title == null || title.isEmpty()) {
            return null;
        }
        return entity.title.containsIgnoreCase(title);
    }

    private BooleanExpression eqContent(String content) {
        QCommunityEntity entity = communityEntity;
        if (content == null || content.isEmpty()) {
            return null;
        }
        return entity.content.containsIgnoreCase(content);
    }
}
