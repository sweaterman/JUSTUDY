package com.justudy.backend.timer.repository;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.timer.domain.QMemberActivityEntity;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberActivityRepositoryImpl implements MemberActivityRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QMemberActivityEntity qMemberActivity = QMemberActivityEntity.memberActivityEntity;


  @Override
  public Tuple findTopTimeByYesterday(Date yesterday) {
    return queryFactory
        .select(qMemberActivity.member, qMemberActivity.time.sum())
        .from(qMemberActivity)
        .where(qMemberActivity.date.eq(yesterday))
        .groupBy(qMemberActivity.member)
        .orderBy(qMemberActivity.time.sum().desc())
        .limit(1).fetchOne();

  }

  @Override
  public Long findTimeByPeriodAndMember(Date ago, Date cur, MemberEntity member) {
    return  (long)queryFactory
        .select(qMemberActivity.time.sum())
        .from(qMemberActivity)
        .where(qMemberActivity.member.eq(member), qMemberActivity.date.gt(ago),
            qMemberActivity.date.loe(cur))
        .groupBy(qMemberActivity.member).fetchOne();
  }

  @Override
  public List<ActivitySubjectResponse> findTimeByCategoryAndMember(MemberEntity member) {
    return queryFactory
        .select(
            Projections.constructor(ActivitySubjectResponse.class, qMemberActivity.category,
                qMemberActivity.time.sum()))
        .from(qMemberActivity)
        .where(qMemberActivity.member.eq(member))
        .groupBy(qMemberActivity.member, qMemberActivity.category)
        .fetch();
  }

  @Override
  public Tuple findAllTimeByPeriod(Date ago, Date cur) {

    return queryFactory
        .select(qMemberActivity.member.countDistinct(), qMemberActivity.time.sum())
        .from(qMemberActivity)
        .where(qMemberActivity.date.gt(ago), qMemberActivity.date.loe(cur))
        .fetchOne();
  }

  @Override
  public List<ActivityCalendarResponse> findCalendarById(Date ago, Date cur,
      MemberEntity member) {
    return queryFactory
        .select(
            Projections.constructor(ActivityCalendarResponse.class,fromDateToDay(qMemberActivity),
                qMemberActivity.time.sum()))
        .from(qMemberActivity)
        .where(qMemberActivity.member.eq(member), qMemberActivity.date.goe(ago),
            qMemberActivity.date.loe(cur))
        .groupBy(qMemberActivity.date)
        .fetch();
  }

  @Override
  public List<MemberActivityBeforeRank> sumTimeByPeriod(Date ago, Date cur) {
    return queryFactory
        .select(Projections.constructor(MemberActivityBeforeRank.class, qMemberActivity.member,
            qMemberActivity.time.sum()))
        .from(qMemberActivity)
        .where(qMemberActivity.date.goe(ago), qMemberActivity.date.loe(cur))
        .groupBy(qMemberActivity.member)
        .orderBy(qMemberActivity.time.sum().desc())
        .limit(10)
        .fetch();
  }
  private DateTemplate fromDateToDay(QMemberActivityEntity qMemberActivity){
    return Expressions.dateTemplate(
        String.class,
        "DATE_FORMAT({0},{1})",
        qMemberActivity.date,
        ConstantImpl.create("%d")
    );
  }

}
