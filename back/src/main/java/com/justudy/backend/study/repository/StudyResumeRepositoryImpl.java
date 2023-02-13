package com.justudy.backend.study.repository;

import com.justudy.backend.member.domain.QMemberEntity;
import com.justudy.backend.study.domain.QStudyEntity;
import com.justudy.backend.study.domain.QStudyResumeEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudyResumeRepositoryImpl implements StudyResumeRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final QStudyResumeEntity qStudyResume = QStudyResumeEntity.studyResumeEntity;
    private final QStudyEntity qStudy = QStudyEntity.studyEntity;
    private final QMemberEntity qMember = QMemberEntity.memberEntity;

    @Override
    public List<StudyResumeEntity> readAllStudyResumeByStudy(Long id) {
        return queryFactory
                .selectFrom(qStudyResume)
                .join(qStudyResume.study,qStudy)
                .fetchJoin()
                .join(qStudyResume.member,qMember)
                .fetchJoin()
                .where(qStudy.sequence.eq(id))
                .fetch();
    }

    @Override
    public List<StudyResumeEntity> readAllStudyResumeByMember(Long id) {
        return queryFactory
                .selectFrom(qStudyResume)
                .join(qStudyResume.study,qStudy)
                .fetchJoin()
                .join(qStudyResume.member,qMember)
                .fetchJoin()
                .where(qMember.sequence.eq(id))
                .fetch();
    }

    @Override
    public Optional<StudyResumeEntity> readStudyResumeByStudyAndMember(Long sequence, Long loginSequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qStudyResume)
                .join(qStudyResume.member, qMember)
                .fetchJoin()
                .join(qStudyResume.study, qStudy)
                .fetchJoin()
                .where(qStudyResume.study.sequence.eq(sequence), qStudyResume.member.sequence.eq(loginSequence))
                .fetchFirst());
    }

    @Override
    public void deleteStudyResumeByStudy(Long id) {
        queryFactory
                .delete(qStudyResume)
                .where(qStudyResume.study.sequence.eq(id))
                .execute();
    }
}
