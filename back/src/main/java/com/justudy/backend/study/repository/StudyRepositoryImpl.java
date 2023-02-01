package com.justudy.backend.study.repository;

import com.justudy.backend.member.domain.QMemberEntity;
import com.justudy.backend.study.domain.QStudyEntity;
import com.justudy.backend.study.domain.QStudyMemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.util.PagingUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

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
                .leftJoin(qStudyEntity.studyMembers, qStudyMemberEntity)
                .fetchJoin()
                .leftJoin(qStudyMemberEntity.member, qMemberEntity)
                .fetchJoin()
                .where(inCategories(sub), eqLeader(studyLeader), eqStudyName(studyName));
        return pagingUtil.getSliceImpl(pageable, query, qStudyEntity.getClass());
    }

    @Override
    public Optional<StudyEntity> findByIdOrderByBadge(Long studySequence) {
//        return queryFactory
//                .selectFrom(qStudyEntity)
//                .leftJoin(qStudyEntity.studyMembers, qStudyMemberEntity)
//                .fetchJoin()
//                .distinct()
//                .orderBy(qStudyMemberEntity.badge.desc())
//                .fetch();
        //현재 사용 X
        return null;
    }

    private BooleanExpression inCategories(List<String> subCategories) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (subCategories == null || subCategories.isEmpty()) {
            return null;
        }
        //todo 여러 카테고리일시 모두 검색
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String sub : subCategories) {

//            if (entity.category.name.containsIgnoreCase(sub)= )
            return entity.category.name.containsIgnoreCase(sub);
        }
        return null;
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
