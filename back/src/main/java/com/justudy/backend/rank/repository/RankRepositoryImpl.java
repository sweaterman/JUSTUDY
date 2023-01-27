package com.justudy.backend.rank.repository;

import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

import com.justudy.backend.community.domain.QCommunityBookmarkEntity;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.domain.QRankEntity;
import com.justudy.backend.rank.domain.RankEntity;
import com.justudy.backend.rank.dto.response.RankResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class RankRepositoryImpl implements RankRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QRankEntity qRank = QRankEntity.rankEntity;

  @Override
  public List<RankResponse> findAllByGroupAndType(RankGroup rankGroup, RankType rankType) {
    return queryFactory
        .select(Projections.constructor(RankResponse.class, qRank.rankOrder, qRank.rankName,
            qRank.rankTime))
        .from(qRank)
        .where(qRank.rankGroup.eq(rankGroup), qRank.rankType.eq(rankType))
        .orderBy(qRank.rankOrder.asc())
        .limit(10)
        .fetch();
  }


}
