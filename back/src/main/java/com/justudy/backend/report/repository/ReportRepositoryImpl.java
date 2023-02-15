package com.justudy.backend.report.repository;

import com.justudy.backend.report.domain.QReport;
import com.justudy.backend.report.domain.Report;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.justudy.backend.report.domain.QReport.*;

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
}
