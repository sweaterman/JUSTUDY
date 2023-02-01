package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class ActivitySubjectResponse {

  private String category;
  private Long second;

  public ActivitySubjectResponse(String category, Long second) {
    this.category = category;
    this.second = second;
  }

}
