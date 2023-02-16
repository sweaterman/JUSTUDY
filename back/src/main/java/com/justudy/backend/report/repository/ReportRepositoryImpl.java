package com.justudy.backend.report.repository;

import com.justudy.backend.report.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.justudy.backend.report.domain.QCommentReport.*;
import static com.justudy.backend.report.domain.QCommunityReport.*;
import static com.justudy.backend.report.domain.QMemberReport.*;
import static com.justudy.backend.report.domain.QReport.*;
import static com.justudy.backend.report.domain.QStudyReport.*;

@RequiredArgsConstructor
@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom {

//    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

//    public Report save(Report report) {
//        entityManager.persist(report);
//        entityManager.flush();
//        return report;
//    }

    @Override
    public List<Report> getReportList(Pageable pageable) {
        return queryFactory.selectFrom(report)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    @Override
    public Long getCountOfReportList() {
        return queryFactory.select(report.count())
                .from(report)
                .fetchOne();
    }

    @Override
    public Optional<Report> findReportBySequence(Long sequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(report)
                .where(report.sequence.eq(sequence))
                .fetchOne());
    }

    @Override
    public Optional<Report> findReportByTargetSequence(Long targetSequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(report)
                .where(report.targetSequence.eq(targetSequence))
                .fetchOne());
    }

    @Override
    public Long getCountOfMemberReport() {
        return queryFactory.select(memberReport.count())
                .from(memberReport)
                .fetchOne();
    }

    @Override
    public Long getCountOfCommunityReport() {
        return queryFactory.select(communityReport.count())
                .from(communityReport)
                .fetchOne();
    }

    @Override
    public Long getCountOfCommentReport() {
        return queryFactory.select(commentReport.count())
                .from(commentReport)
                .fetchOne();
    }

    @Override
    public Long getCountOfStudyReport() {
        return queryFactory.select(studyReport.count())
                .from(studyReport)
                .fetchOne();
    }
}
