package com.justudy.backend.timer.repository;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.timer.domain.QRoomActivityEntity;
import com.justudy.backend.timer.domain.RoomActivityEntity;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.justudy.backend.timer.dto.response.RoomActivityBeforeRank;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomActivityRepositoryImpl implements RoomActivityRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QRoomActivityEntity qRoomActivityEntity = QRoomActivityEntity.roomActivityEntity;


  @Override
  public List<ActivityCalendarResponse> findCalendarById(Date ago, Date cur,
      StudyRoomEntity studyRoom) {

    return queryFactory
        .select(
            Projections.constructor(ActivityCalendarResponse.class,
                fromDateToDay(qRoomActivityEntity),
                qRoomActivityEntity.time.sum()))
        .from(qRoomActivityEntity)
        .where(qRoomActivityEntity.studyRoom.eq(studyRoom), qRoomActivityEntity.date.goe(ago),
            qRoomActivityEntity.date.loe(cur))
        .groupBy(qRoomActivityEntity.date)
        .fetch();

  }

  @Override
  public List<RoomActivityBeforeRank> sumTimeByPeriod(Date ago, Date cur) {
    return queryFactory
        .select(Projections.constructor(RoomActivityBeforeRank.class, qRoomActivityEntity.studyRoom,
            qRoomActivityEntity.time.sum()))
        .from(qRoomActivityEntity)
        .where(qRoomActivityEntity.date.goe(ago), qRoomActivityEntity.date.loe(cur))
        .groupBy(qRoomActivityEntity.studyRoom)
        .orderBy(qRoomActivityEntity.time.sum().desc())
        .limit(10)
        .fetch();
  }

  @Override
  public List<RoomActivityEntity> findByStudyRoom(Long roomSeq) {
    return queryFactory
            .selectFrom(qRoomActivityEntity)
            .where(qRoomActivityEntity.studyRoom.sequence.eq(roomSeq))
            .fetch();
  }

  @Override
  public List<ActivitySubjectResponse> sumAllTimeByCategory() {
    return queryFactory
        .select(Projections.constructor(ActivitySubjectResponse.class, qRoomActivityEntity.category,
            qRoomActivityEntity.time.sum()))
        .from(qRoomActivityEntity)
        .groupBy(qRoomActivityEntity.category)
        .fetch();
  }

  private DateTemplate fromDateToDay(QRoomActivityEntity qRoomActivity) {
    return Expressions.dateTemplate(
        String.class,
        "DATE_FORMAT({0},{1})",
        qRoomActivity.date,
        ConstantImpl.create("%d")
    );
  }
}
