package com.justudy.backend.GroupCall.repository;

import com.justudy.backend.GroupCall.domain.QStudyRoomEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyRoomRepositoryImpl implements StudyRoomRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QStudyRoomEntity qStudyRoomEntity;

  @Override
  public String findUUIDByStudy(StudyEntity study) {

    return queryFactory
        .select(qStudyRoomEntity.studyRoomUUID)
        .from(qStudyRoomEntity)
        .where(qStudyRoomEntity.studyEntity.eq(study))
        .fetchOne();
  }
}
