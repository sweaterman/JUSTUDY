package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class ActivityToRank {

  private Integer order;
  private Long second;

  private String name;
  private Long image;

  public ActivityToRank(Integer order, Long second, String name, Long image) {
    this.order = order;
    this.second = second;
    this.name = name;
    this.image = image;
  }
}
