package com.justudy.backend.timer.repository;

import com.justudy.backend.timer.domain.QRoomActivityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomActivityRepositoryImpl implements RoomActivityRepositoryCustom{
  private final JPAQueryFactory queryFactory;
  private final QRoomActivityEntity qRoomActivityEntity = QRoomActivityEntity.roomActivityEntity;
}
