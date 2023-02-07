package com.justudy.backend.study.repository;

import com.justudy.backend.member.domain.QMemberEntity;
import com.justudy.backend.study.domain.QStudyEntity;
import com.justudy.backend.study.domain.QStudyMemberEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudyMemberRepositoryImpl implements StudyMemberRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final QStudyMemberEntity qStudyMemberEntity = QStudyMemberEntity.studyMemberEntity;
    private final QStudyEntity qStudyEntity = QStudyEntity.studyEntity;
    private final QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

    @Override
    public void deleteStudyMember(Long id, Long memberId) {
        queryFactory
                .delete(qStudyMemberEntity)
                .where(qStudyMemberEntity.study.sequence.eq(id), qStudyMemberEntity.member.sequence.eq(memberId))
                .execute();
    }

    @Override
    public List<StudyMemberEntity> readAllRegisterStudy(Long id) {
        return queryFactory
                .selectFrom(qStudyMemberEntity)
                .join(qStudyMemberEntity.study, qStudyEntity)
                .fetchJoin()
                .join(qStudyMemberEntity.member, qMemberEntity)
                .fetchJoin()
                .where(qStudyMemberEntity.member.sequence.eq(id))
                .fetch();
    }

    @Override
    public void deleteStudyMemberByStudy(Long studySequence) {
        queryFactory
                .delete(qStudyMemberEntity)
                .where(qStudyMemberEntity.study.sequence.eq(studySequence))
                .execute();
    }

    @Override
    public Optional<StudyMemberEntity> readStudyMemberById(Long id, Long memberId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qStudyMemberEntity)
                .join(qStudyMemberEntity.study, qStudyEntity)
                .fetchJoin()
                .join(qStudyMemberEntity.member, qMemberEntity)
                .fetchJoin()
                .where(qMemberEntity.sequence.eq(memberId), qStudyEntity.sequence.eq(id))
                .fetchFirst());
    }
}
