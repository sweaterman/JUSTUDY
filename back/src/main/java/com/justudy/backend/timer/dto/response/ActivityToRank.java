package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class ActivityToRank {

  private Integer order;
  private Long second;
  private String name;
  private Long image;
  private Long sequence;

  public ActivityToRank(Integer order, Long second, String name, Long image, Long sequence) {
    this.order = order;
    this.second = second;
    this.name = name;
    this.image = image;
    this.sequence = sequence;
  }
}
