package com.justudy.backend.rank.repository;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.dto.response.RankResponse;
import java.util.List;

public interface RankRepositoryCustom {

  List<RankResponse> findAllByGroupAndType(RankGroup rankGroup, RankType rankType);

  void deleteAllByGroupAndType(RankGroup rankGroup, RankType rankType);

}
