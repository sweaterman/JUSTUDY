package com.justudy.backend.studyMember.repository;

import com.justudy.backend.community.domain.QCommunityEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class StudyMemberMemberImpl implements StudyMemberSupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QCommunityEntity qCommunity = QCommunityEntity.communityEntity;
    private final long MAX_POPULAR_SIZE = 20;

    @Override
    public Page<StudyMemberEntity> findAllByNotice(Pageable pageable) {
//        JPQLQuery<StudyMemberEntity> query = queryFactory
//                .selectFrom(qCommunity)
//                .where(qCommunity.isHighlighted.eq(true))
//                .orderBy(qCommunity.createdTime.desc());
//        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
        return null;
    }

    @Override
    public Page<StudyMemberEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
//        if (!(name == null || name.isEmpty())) {
//            JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(qCommunity)
////                    .join(qCommunity.member_seq, member)
//                    .where(qCommunity.member.nickname.containsIgnoreCase(name));
//            return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
//        }
        //name이 없을 경우 title과 content 탐색
//        JPQLQuery<StudyMemberEntity> query = queryFactory
//                .selectFrom(qCommunity)
////                .join(community.member_seq, member)
//                .where(eqNickname(name), eqTitle(title), eqContent(content));
//        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
        return null;
    }

    @Override
    public Page<StudyMemberEntity> findAll(Pageable pageable, String category) {
//        JPQLQuery<StudyMemberEntity> query = queryFactory
//                .selectFrom(qCommunity)
//                .where(qCommunity.category_seq.eq(Long.valueOf(category)));
//
//        return pagingUtil.getPageImpl(pageable, query, qCommunity.getClass());
        return null;
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
    public List<StudyMemberEntity> findPopularCommunity() {
//        return queryFactory
//                .selectFrom(qCommunity)
//                .orderBy(qCommunity.weekLoveCount.desc())
//                .limit(MAX_POPULAR_SIZE)
//                .fetch();
        return null;
    }


    private BooleanExpression eqNickname(String name) {
        QCommunityEntity entity = QCommunityEntity.communityEntity;
        if (name == null || name.isEmpty()) {
            return null;
        }
        return entity.member.nickname.containsIgnoreCase(name);
    }

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
