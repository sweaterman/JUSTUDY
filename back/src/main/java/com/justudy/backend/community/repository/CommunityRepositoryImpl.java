package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.QCommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.exception.ImportBoardFail;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.justudy.backend.category.domain.QCategoryEntity.categoryEntity;
import static com.justudy.backend.community.domain.QCommunityEntity.communityEntity;
import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QCommunityEntity qCommunity = communityEntity;
    private final long MAX_POPULAR_SIZE = 20;


    @Override
    public List<CommunityEntity> getAllList(CommunitySearch communitySearch) {
        List<CommunityEntity> list = queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(true))
                .limit(communitySearch.getNoticeBoardSize())
                .orderBy(communityEntity.sequence.desc())
                .fetch();

        List<CommunityEntity> commonList = queryFactory.selectFrom(communityEntity)
                .join(communityEntity.member, memberEntity).fetchJoin()
                .join(communityEntity.category, categoryEntity).fetchJoin()
                .where(communityEntity.isHighlighted.eq(false),
                        eqCategory(communitySearch.getCategory()))
                .limit(communitySearch.getSize() - list.size())
                .offset(communitySearch.getOffsetWithNotice(list.size()))
                .orderBy(communityEntity.sequence.desc())
                .fetch();
        if (!list.addAll(commonList)) {
            throw new ImportBoardFail("community", "게시글 리스트 가져오기 실패");
        }
        return list;
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
