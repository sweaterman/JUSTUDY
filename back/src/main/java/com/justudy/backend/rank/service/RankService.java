package com.justudy.backend.rank.service;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.domain.RankEntity;
import com.justudy.backend.rank.dto.response.RankResponse;
import com.justudy.backend.rank.repository.RankRepository;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

  private final RankRepository rankRepository;

  @Transactional
  public List<RankResponse> readAllByGroupAndType(RankGroup rankGroup, RankType rankType) {
    return rankRepository.findAllByGroupAndType(rankGroup, rankType);
  }

  @Transactional
  public void deleteAllByGroupAndType(RankGroup rankGroup, RankType rankType) {
    rankRepository.deleteAllByGroupAndType(rankGroup, rankType);
  }

  @Transactional
  public void saveRank(RankGroup rankGroup, RankType rankType,
      List<ActivityToRank> memberActivityToRankList) {
    for (ActivityToRank memberActivityToRank : memberActivityToRankList) {
      rankRepository.save(RankEntity.builder()
          .rankGroup(rankGroup)
          .rankType(rankType)
          .rankOrder(memberActivityToRank.getOrder())
          .rankName(memberActivityToRank.getName())
          .rankTime(memberActivityToRank.getSecond())
          .rankImage(memberActivityToRank.getImage())
          .rankTargetSeq(memberActivityToRank.getSequence())
          .build());
    }
  }
}
