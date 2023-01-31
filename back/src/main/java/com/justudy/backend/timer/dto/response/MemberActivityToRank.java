package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivityToRank {

  private Integer order;
  private String time;

  private String name;
  private Long image;

  public MemberActivityToRank(Integer order, String time, String name, Long image) {
    this.order = order;
    this.time = time;
    this.name = name;
    this.image = image;
  }
}
