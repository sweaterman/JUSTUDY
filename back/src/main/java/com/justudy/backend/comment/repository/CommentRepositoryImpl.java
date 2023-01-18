package com.justudy.backend.comment.repository;

import com.justudy.backend.comment.domain.CommentEntity;
import com.justudy.backend.comment.domain.QCommentEntity;
import com.justudy.backend.comment.dto.request.CommentEdit;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositorySupport {
    private final JPAQueryFactory queryFactory;

    private final QCommentEntity qComment = QCommentEntity.commentEntity;

    @Override
    public Page<CommentEntity> findAllByNotice(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CommentEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content) {
        return null;
    }

    @Override
    public Page<CommentEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Long saveComment(Long id, CommentEntity comment) {
        return queryFactory
                .insert(qComment)
                .columns(qComment.memberSeq, qComment.communitySeq, qComment.content, qComment.createdTime, qComment.modifiedTime, qComment.isDeleted, qComment.group, qComment.order, qComment.parentSeq, qComment.step, qComment.childNumber)
                .values(comment.getMemberSeq(), comment.getCommunitySeq(), comment.getContent(), comment.getCreatedTime(), comment.getModifiedTime(), comment.getIsDeleted(), comment.getGroup(), comment.getOrder(), comment.getParentSeq(), comment.getStep(), comment.getChildNumber())
                .execute();
    }

    @Override
    public Optional<CommentEntity> readComment(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qComment)
                .where(qComment.sequence.eq(id))
                .fetchOne());
    }


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
