package com.justudy.backend.rank.dto.response;

import com.justudy.backend.rank.domain.RankEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import lombok.NoArgsConstructor;

@Data
public class RankResponse {

  private Integer rankOrder;
  private String rankName;
  private Time rankTime;

  public RankResponse(Integer rankOrder, String rankName, Time rankTime) {
    this.rankOrder = rankOrder;
    this.rankName = rankName;
    this.rankTime = rankTime;
  }
}
