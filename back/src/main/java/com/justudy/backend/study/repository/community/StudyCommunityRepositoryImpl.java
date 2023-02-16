package com.justudy.backend.study.repository.community;

import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.request.SearchOrderType;
import com.justudy.backend.community.dto.request.SearchType;
import com.justudy.backend.community.exception.ImportBoardFail;
import com.justudy.backend.study.domain.community.QStudyCommunityEntity;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@Slf4j
@RequiredArgsConstructor
public class StudyCommunityRepositoryImpl implements StudyCommunityRepositoryCustom {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QStudyCommunityEntity qStudyCommunity = QStudyCommunityEntity.studyCommunityEntity;


    @Override
    public List<StudyCommunityEntity> getAllList(CommunitySearch communitySearch, Long studySequence) {
        List<StudyCommunityEntity> list = new ArrayList<>();
        List<StudyCommunityEntity> notices = queryFactory.selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.isHighlighted.eq(true),
                        qStudyCommunity.isDeleted.eq(false),
                        eqStudy(studySequence))
                .limit(communitySearch.getNoticeBoardSize())
                .orderBy(qStudyCommunity.sequence.desc())
                .fetch();

        List<StudyCommunityEntity> commonList = queryFactory.selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.isHighlighted.eq(false),
                        qStudyCommunity.isDeleted.eq(false),
                        eqStudy(studySequence),
                        eqTypeAndSearch(communitySearch))
                .limit(communitySearch.getSize() - notices.size())
                .offset(communitySearch.getOffsetWithNotice(notices.size()))
                .orderBy(orderByCondition(communitySearch))
                .fetch();

        list.addAll(notices);
        list.addAll(commonList);
        return list;
    }

    @Override
    public Long getCountOfList(CommunitySearch communitySearch, Long studySequence) {
        return queryFactory.select(qStudyCommunity.count())
                .from(qStudyCommunity)
                .where(qStudyCommunity.isHighlighted.eq(false),
                        qStudyCommunity.isDeleted.eq(false),
                        eqStudy(studySequence),
                        eqTypeAndSearch(communitySearch))
                .fetchFirst();
    }

    @Override
    public List<StudyCommunityEntity> getAllNotice(Pageable pageable) {
        return queryFactory.selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.isHighlighted.eq(true),
                        qStudyCommunity.isDeleted.eq(false))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qStudyCommunity.sequence.desc())
                .fetch();
    }

    @Override
    public Long getCountOfNotices() {
        return queryFactory.select(qStudyCommunity.count())
                .from(qStudyCommunity)
                .where(qStudyCommunity.isHighlighted.eq(true),
                        qStudyCommunity.isDeleted.eq(false))
                .fetchFirst();
    }

    public List<StudyCommunityEntity> getMostLoveListOfWeek(Pageable pageable) {
        return queryFactory.selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.isHighlighted.eq(false),
                        qStudyCommunity.isDeleted.eq(false))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qStudyCommunity.weekLoveCount.desc())
                .fetch();
    }

    @Override
    public Optional<StudyCommunityEntity> findBySequence(Long sequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.sequence.eq(sequence),
                        qStudyCommunity.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public List<StudyCommunityEntity> getListBySequences(List<Long> sequences) {
        return queryFactory
                .selectFrom(qStudyCommunity)
                .join(qStudyCommunity.member, memberEntity).fetchJoin()
                .where(qStudyCommunity.sequence.in(sequences),
                        qStudyCommunity.isDeleted.eq(false))
                .fetch();
    }


    @Override
    public Page<StudyCommunityEntity> findAllByNotice(Pageable pageable) {
        JPQLQuery<StudyCommunityEntity> query = queryFactory
                .selectFrom(qStudyCommunity)
                .where(qStudyCommunity.isHighlighted.eq(true))
                .orderBy(qStudyCommunity.createdTime.desc());
        return pagingUtil.getPageImpl(pageable, query, qStudyCommunity.getClass());
    }

    @Override
    public Page<StudyCommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
//        if (!(name == null || name.isEmpty())) {
//            JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(qCommunity)
////                    .join(qCommunity.member_seq, member)
//                    .where(qCommunity.member.nickname.containsIgnoreCase(name));
//            return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
//        }
        //name이 없을 경우 title과 content 탐색
        JPQLQuery<StudyCommunityEntity> query = queryFactory
                .selectFrom(qStudyCommunity)
//                .join(community.member_seq, member)
                .where(eqNickname(name), eqTitle(title), eqContent(content));
        return pagingUtil.getPageImpl(pageable, query, qStudyCommunity.getClass());
    }
    @Override
    public Long noticeCount() {
        return queryFactory
                .select(qStudyCommunity.count())
                .from(qStudyCommunity)
                .where(qStudyCommunity.isHighlighted.eq(true))
                .fetchFirst();
    }

    @Override
    public List<StudyCommunityEntity> findPopularCommunity() {
        long MAX_POPULAR_SIZE = 20;
        return queryFactory
                .selectFrom(qStudyCommunity)
                .orderBy(qStudyCommunity.weekLoveCount.desc())
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
            return qStudyCommunity.sequence.desc();
        }
        return orderType.getOrderSpecifier();
    }

    private BooleanExpression eqStudy(Long studySeq) {
        return qStudyCommunity.study.sequence.eq(studySeq);
    }


    private BooleanExpression eqNickname(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return qStudyCommunity.member.nickname.containsIgnoreCase(name);
    }

    private BooleanExpression eqTitle(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return qStudyCommunity.title.containsIgnoreCase(title);
    }

    private BooleanExpression eqContent(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        return qStudyCommunity.content.containsIgnoreCase(content);
    }
}
