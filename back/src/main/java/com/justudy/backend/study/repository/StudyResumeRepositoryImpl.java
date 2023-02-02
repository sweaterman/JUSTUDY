package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.QStudyEntity;
import com.justudy.backend.study.domain.QStudyResumeEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
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
public class StudyResumeRepositoryImpl implements StudyResumeRepositorySupport {

    private final PagingUtil pagingUtil;
    private final JPAQueryFactory queryFactory;
    private final QStudyResumeEntity qStudyResume = QStudyResumeEntity.studyResumeEntity;

    @Override
    public List<StudyResumeEntity> readAllStudyResumeByStudy(Long id) {
        return queryFactory
                .selectFrom(qStudyResume)
                .where(qStudyResume.study.sequence.eq(id))
                .fetch();
    }

    @Override
    public List<StudyResumeEntity> readAllStudyResumeByMember(Long id) {
        return queryFactory
                .selectFrom(qStudyResume)
                .where(qStudyResume.member.sequence.eq(id))
                .fetch();
    }
}
