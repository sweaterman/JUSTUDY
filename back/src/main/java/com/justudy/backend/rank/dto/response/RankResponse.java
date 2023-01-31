package com.justudy.backend.rank.dto.response;

import com.justudy.backend.rank.domain.RankEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import lombok.NoArgsConstructor;

@Data
public class RankResponse {

  private Integer order;
  private String nickName;
  private Time time;
  private String img;

  public RankResponse(Integer rankOrder, String rankName, Time rankTime, String rankImage) {
    this.order = rankOrder;
    this.nickName = rankName;
    this.time = rankTime;
    this.img = rankImage;
  }
}
