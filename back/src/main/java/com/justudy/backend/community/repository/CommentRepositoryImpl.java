package com.justudy.backend.community.repository;

import com.justudy.backend.comment.domain.CommentEntity;
import com.justudy.backend.comment.domain.QCommentEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositorySupport {
    private final JPAQueryFactory queryFactory;

    private final QCommentEntity qComment = QCommentEntity.commentEntity;

    @Override
    public List<CommentEntity> readAllComment(long id) {
        return queryFactory
                .selectFrom(qComment)
                .where(qComment.communitySeq.eq(id))
                .orderBy(qComment.group.asc(), qComment.order.asc())
                .fetch();

    }

    @Override
    public Integer findByGroup(Long communitySeq) {
        Integer group = queryFactory.
                select(qComment.group)
                .from(qComment)
                .where(qComment.communitySeq.eq(communitySeq))
                .orderBy(qComment.group.desc())
                .fetchFirst();
        return group == null ? 0 : group;
    }

    @Override
    public void updateChildNumber(Long sequence, Integer childNumber) {
        queryFactory
                .update(qComment)
                .set(qComment.childNumber, childNumber + 1)
                .where(qComment.sequence.eq(sequence))
                .execute();
    }

    @Override
    public Integer findByChildNumberSum(Integer group) {
        return queryFactory
                .select(qComment.childNumber.sum())
                .from(qComment)
                .where(qComment.group.eq(group))
                .fetchOne();
    }

    @Override
    public Integer findByMaxStep(Integer group) {
        return queryFactory
                .select(qComment.step.max())
                .from(qComment)
                .where(qComment.group.eq(group))
                .fetchOne();
    }

    @Override
    public void updateOrderPlus(Integer group, int order) {
        queryFactory
                .update(qComment)
                .set(qComment.order, qComment.order.add(1))
                .where(qComment.group.eq(group), qComment.order.gt(order))
                .execute();

    }
}
