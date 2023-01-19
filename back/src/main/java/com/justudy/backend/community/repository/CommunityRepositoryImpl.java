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
public class CommunityRepositoryImpl implements CommunityRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QCommunityEntity qCommunity = QCommunityEntity.communityEntity;

    @Override
    public Page<CommunityEntity> findAllByNotice(Pageable pageable) {
        JPQLQuery<CommunityEntity> query = queryFactory
                .selectFrom(qCommunity)
                .where(qCommunity.isHighlighted.eq(true));
        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }

    @Override
    public Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
        //name이 있을 경우 member와 join 후 닉내임으로 탐색
        //member가 없어서 구현X
        if (!(name == null || name.isEmpty())) {
//            JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(community)
//                    .join(community.member_seq, member)
//                    .where(community.nickname.containsIgnoreCase(name));
//            return pagingUtil.getPageImpl(pageable,query,community.getClass());
        }
        //name이 없을 경우 title과 content 탐색
        JPQLQuery<CommunityEntity> query = queryFactory
                .selectFrom(qCommunity)
//                .join(community.member_seq, member)
                .where(eqTitle(title), eqContent(content));
        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }

    @Override
    public Page<CommunityEntity> findAll(Pageable pageable,String category) {
        JPQLQuery<CommunityEntity> query = queryFactory
                .selectFrom(qCommunity)
                .where(qCommunity.category_seq.eq(Long.valueOf(category)));

        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
    }


    //    private BooleanExpression eqWriter(String name) {
//        QCommunityEntity entity = QCommunityEntity.communityEntity;
//        if (name == null || name.isEmpty()) {
//            return null;
//        }
//        return entity.nickname.containsIgnoreCase(name);
//    }
    private BooleanExpression eqTitle(String title) {
        QCommunityEntity entity = QCommunityEntity.communityEntity;
        if (title == null || title.isEmpty()) {
            return null;
        }
        return entity.title.containsIgnoreCase(title);
    }

    private BooleanExpression eqContent(String content) {
        QCommunityEntity entity = QCommunityEntity.communityEntity;
        if (content == null || content.isEmpty()) {
            return null;
        }
        return entity.content.containsIgnoreCase(content);
    }
}
