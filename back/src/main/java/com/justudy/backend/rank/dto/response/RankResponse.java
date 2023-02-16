package com.justudy.backend.rank.dto.response;

import lombok.Data;

@Data
public class RankResponse {

  private Integer order;
  private String nickName;
  private Long time;
  private Long img;
  private Long seq;

  public RankResponse(Integer rankOrder, String rankName, Long rankTime, Long rankImage, Long seq) {
    this.order = rankOrder;
    this.nickName = rankName;
    this.time = rankTime;
    this.img = rankImage;
    this.seq = seq;
  }
}
