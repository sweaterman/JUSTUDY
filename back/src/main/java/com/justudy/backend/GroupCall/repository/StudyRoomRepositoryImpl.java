package com.justudy.backend.GroupCall.repository;

import com.justudy.backend.GroupCall.domain.QStudyRoomEntity;
import com.justudy.backend.GroupCall.dto.response.StudyRoomResponse;
import com.justudy.backend.study.domain.StudyEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyRoomRepositoryImpl implements StudyRoomRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QStudyRoomEntity qStudyRoomEntity = QStudyRoomEntity.studyRoomEntity;

  @Override
  public StudyRoomResponse findUUIDByStudy(StudyEntity study) {

    return queryFactory
        .select(
            Projections.constructor(StudyRoomResponse.class, qStudyRoomEntity.studyRoomUUID,
                qStudyRoomEntity.sequence))
        .from(qStudyRoomEntity)
        .where(qStudyRoomEntity.studyEntity.eq(study))
        .fetchOne();
  }
}
