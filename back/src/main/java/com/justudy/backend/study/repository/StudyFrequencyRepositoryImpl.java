package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.QStudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudyFrequencyRepositoryImpl implements StudyFrequencyRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final QStudyFrequencyEntity qStudyFrequencyEntity = QStudyFrequencyEntity.studyFrequencyEntity;

    @Override
    public List<StudyFrequencyEntity> readAllStudyFrequency(Long studySequence) {
        return queryFactory
                .selectFrom(qStudyFrequencyEntity)
                .where(qStudyFrequencyEntity.study.sequence.eq(studySequence))
                .fetch();
    }

    @Override
    public void deleteByStudy(Long studyId) {
        queryFactory
                .delete(qStudyFrequencyEntity)
                .where(qStudyFrequencyEntity.study.sequence.eq(studyId))
                .execute();
    }

}
