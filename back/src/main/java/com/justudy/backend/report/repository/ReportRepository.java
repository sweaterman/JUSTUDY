package com.justudy.backend.report.repository;

import com.justudy.backend.report.domain.Report;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReportRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public Report save(Report report) {
        entityManager.persist(report);
        entityManager.flush();
        return report;
    }

//    public List<Report> getReportList() {
//        List<Report> list = new ArrayList<>();
//        queryFactory.select();
//        return null;
//    }
}
