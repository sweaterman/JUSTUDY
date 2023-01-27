package com.justudy.backend.timer.repository;

import com.justudy.backend.member.domain.QMemberEntity;
import com.justudy.backend.timer.domain.QMemberActivityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberActivityRepositoryImpl implements MemberActivityRepositoryCustom {
  private final JPAQueryFactory queryFactory;
  private final QMemberActivityEntity qMemberActivity = QMemberActivityEntity.memberActivityEntity;
}
