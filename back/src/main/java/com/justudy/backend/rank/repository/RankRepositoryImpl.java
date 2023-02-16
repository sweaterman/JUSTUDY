package com.justudy.backend.rank.repository;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.domain.QRankEntity;
import com.justudy.backend.rank.dto.response.RankResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RankRepositoryImpl implements RankRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QRankEntity qRank = QRankEntity.rankEntity;

  @Override
  public List<RankResponse> findAllByGroupAndType(RankGroup rankGroup, RankType rankType) {
    return queryFactory
        .select(Projections.constructor(RankResponse.class, qRank.rankOrder, qRank.rankName,
            qRank.rankTime, qRank.rankImage,qRank.sequence))
        .from(qRank)
        .where(qRank.rankGroup.eq(rankGroup), qRank.rankType.eq(rankType))
        .orderBy(qRank.rankOrder.asc())
        .limit(10)
        .fetch();
  }

  @Override
  public void deleteAllByGroupAndType(RankGroup rankGroup, RankType rankType) {
    queryFactory
        .delete(qRank)
        .where(qRank.rankGroup.eq(rankGroup), qRank.rankType.eq(rankType))
        .execute();
  }



}
