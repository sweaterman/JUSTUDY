package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.QStudyCommunityCommentEntity;
import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudyCommunityCommentRepositoryImpl implements StudyCommunityCommentRepositorySupport {
    private final JPAQueryFactory queryFactory;

    private final QStudyCommunityCommentEntity qComment = QStudyCommunityCommentEntity.studyCommunityCommentEntity;

    @Override
    public List<StudyCommunityCommentEntity> readAllComment(long id) {
//        List<CommunityCommentEntity> list =queryFactory
//                .selectFrom(qComment)
//                .where(qComment.community.sequence.eq(id))
//                .orderBy(qComment.group.asc(),qComment.sequence.asc())
//                .fetch();
//
//        Logger log;
//        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
//        for (int i = 0; i < 10; i++) {
//
//            log.info("정보5 seq: {},par : {}, group: {}, ordre: {}", list.get(i).getSequence(), list.get(i).getParentSeq(),list.get(i).getGroup(),list.get(i).getOrder());
//        }
//        return list;
        return queryFactory
                .selectFrom(qComment)
                .where(qComment.community.sequence.eq(id))
                .orderBy(qComment.group.asc(), qComment.order.asc())
                .fetch();

    }

    @Override
    public Integer findByGroup(Long communitySeq) {
        Integer group = queryFactory.
                select(qComment.group)
                .from(qComment)
                .where(qComment.community.sequence.eq(communitySeq))
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
