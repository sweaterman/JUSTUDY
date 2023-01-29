package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.QStudyEntity;
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

@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QStudyEntity qStudy = QStudyEntity.studyEntity;

    @Override
    public Slice<StudyEntity> findAllBySearchOption(Pageable pageable, List<String> sub, Long leaderSeq, String studyName) {
        JPQLQuery<StudyEntity> query = queryFactory
                .selectFrom(qStudy)
                .where(inCategories(sub), eqLeader(leaderSeq), eqStudyName(studyName));
        return pagingUtil.getSliceImpl(pageable, query, qStudy.getClass());
    }

    private BooleanExpression inCategories(List<String> subCategories) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (subCategories == null || subCategories.isEmpty()) {
            return null;
        }
        //todo ??
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String sub : subCategories) {

//            if (entity.category.name.containsIgnoreCase(sub)= )
                return entity.category.name.containsIgnoreCase(sub);
        }
        return null;
    }


    private BooleanExpression eqLeader(Long leaderSeq) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (leaderSeq == null || leaderSeq == 0) {
            return null;
        }
        return entity.leaderSeq.eq(leaderSeq);
    }

    private BooleanExpression eqStudyName(String studyName) {
        QStudyEntity entity = QStudyEntity.studyEntity;
        if (studyName == null || studyName.isEmpty()) {
            return null;
        }
        return entity.name.containsIgnoreCase(studyName);
    }
}
