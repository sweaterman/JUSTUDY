package com.justudy.backend.study.repository;

import com.justudy.backend.member.domain.QMemberEntity;
import com.justudy.backend.study.domain.QStudyEntity;
import com.justudy.backend.study.domain.QStudyMemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QStudyEntity qStudyEntity = QStudyEntity.studyEntity;
    private final QStudyMemberEntity qStudyMemberEntity = QStudyMemberEntity.studyMemberEntity;
    private final QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

    @Override
    public Slice<StudyEntity> findAllBySearchOption(Pageable pageable, List<String> sub, String studyLeader, String studyName) {
        JPQLQuery<StudyEntity> query = queryFactory
                .selectFrom(qStudyEntity)
                .join(qStudyEntity.studyMembers, qStudyMemberEntity)
                .fetchJoin()
                .join(qStudyMemberEntity.member, qMemberEntity)
                .fetchJoin()
                .where(inCategories(sub), eqLeader(studyLeader), eqStudyName(studyName), qStudyEntity.isOpen.eq(true))
                .orderBy(qStudyEntity.createdTime.desc());

        return pagingUtil.getSliceImpl(pageable, query, qStudyEntity.getClass());
    }

    @Override
    public StudyEntity findByLeaderSeq(Long leaderSeq) {
        return queryFactory
                .selectFrom(qStudyEntity)
                .where(qStudyEntity.leaderSeq.eq(leaderSeq))
                .fetchFirst();
    }

    @Override
    public StudyEntity readStudyByNickName(String name) {
        return queryFactory
                .selectFrom(qStudyEntity)
                .where(qStudyEntity.name.eq(name))
                .fetchFirst();
    }

    private BooleanExpression inCategories(List<String> subCategories) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (subCategories == null || subCategories.isEmpty()) {
            return null;
        }
        return entity.category.key.in(subCategories);
    }


    private BooleanExpression eqLeader(String studyLeader) {
        QMemberEntity entity = QMemberEntity.memberEntity;
        if (studyLeader == null || studyLeader.isEmpty()) {
            return null;
        }
        return entity.username.containsIgnoreCase(studyLeader);
    }

    private BooleanExpression eqStudyName(String studyName) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (studyName == null || studyName.isEmpty()) {
            return null;
        }
        return entity.name.containsIgnoreCase(studyName);
    }
}
