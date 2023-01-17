package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLove;
import com.justudy.backend.community.domain.QCommunityEntity;
import com.justudy.backend.community.domain.QCommunityLove;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CommunityEntity> findAllByNotice(Pageable pageable) {
        QCommunityEntity community = QCommunityEntity.communityEntity;
        JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(community)
                .where(community.isHighlighted.eq(true));
        return pagingUtil.getPageImpl(pageable,query,community.getClass());
    }

    @Override
    public Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
        QCommunityEntity community = QCommunityEntity.communityEntity;
        //name이 있을 경우 member와 join 후 닉내임으로 탐색
        if (!(name == null || name.isEmpty())) {
//            JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(community)
//                    .join(community.member_seq, member)
//                    .where(community.nickname.containsIgnoreCase(name));
//            return pagingUtil.getPageImpl(pageable,query,community.getClass());
        }
        //name이 없을 경우 title과 content 탐색
        JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(community)
//                .join(community.member_seq, member)
                .where(eqTitle(title), eqContent(content));
        return pagingUtil.getPageImpl(pageable,query,community.getClass());
    }

    @Override
    public Page<CommunityEntity> findAll(Pageable pageable) {
        QCommunityEntity community = QCommunityEntity.communityEntity;
//        List<CommunityEntity> content = queryFactory.select(community)
//                .from(community)
////                .join()
////                .fetchJoin()
//                .orderBy(CommunitySort(pageable))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//                long total = queryFactory.select(community)
//                .from(community)
//                .fetchCount();
        JPQLQuery<CommunityEntity> query = queryFactory.selectFrom(community);

        return pagingUtil.getPageImpl(pageable, query, community.getClass());
    }



    @Override
    public long saveLove(CommunityCreate request) {
        QCommunityLove love = QCommunityLove.communityLove;
        return queryFactory
                .insert(love)
                .columns(love.community,love.isChecked)
                .values(CommunityEntity.builder().category_seq(request.getCategory_seq()).member_seq(request.getMember_seq()).build(),true)
                .execute();
    }

    @Override
    public Long updateLove(CommunityEdit request,boolean flag) {
        QCommunityLove love = QCommunityLove.communityLove;
        return queryFactory
                .update(love)
                .where(love.community.sequence.eq(request.getSequence()),love.community.member_seq.eq(request.getMember_seq()))
                .set(love.isChecked,flag)
                .execute();
    }

    @Override
    public List<CommunityLove> readAllLoveByCommunity(Long id) {
        QCommunityLove love = QCommunityLove.communityLove;
        return queryFactory
                .selectFrom(love)
                .where(love.community.sequence.eq(id))
                .fetch();
    }

    @Override
    public Long deleteAllLoveByCommunity(Long id) {
        QCommunityLove love = QCommunityLove.communityLove;
        return queryFactory
                .delete(love)
                .where(love.community.sequence.eq(id))
                .execute();
    }

    @Override
    public boolean readLove(CommunityEdit request) {
        QCommunityLove love = QCommunityLove.communityLove;
        return queryFactory
                .select(love.isChecked)
                .from(love)
                .where(love.community.sequence.eq(request.getSequence()),love.community.member_seq.eq(request.getMember_seq()))
                .fetchOne();
    }

    /**
     * pagenation을 위한 community sort
     *
     * @param
     * @return
     */
//    private OrderSpecifier<?> CommunitySort(Pageable page) {
//        QCommunityEntity community = QCommunityEntity.communityEntity;
//        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
//        if (!page.getSort().isEmpty()) {
//            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
//            for (Sort.Order order : page.getSort()) {
//                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
//                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
//                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
//                switch (order.getProperty()) {
//                    case "createdAt":
//                    case "descending":
//                        return new OrderSpecifier(direction, community.createdAt);
//                    case "countOfLikes":
//                        return new OrderSpecifier(direction, community.countOfLikes);
//                    case "view":
//                        return new OrderSpecifier(direction, community.view);
//
//                }
//            }
//        }
//        return null;
//    }

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
