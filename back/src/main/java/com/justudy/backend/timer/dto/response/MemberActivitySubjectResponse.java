package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivitySubjectResponse {
  private String category;
  private String time;

  public MemberActivitySubjectResponse(String category, String time) {
    this.category = category;
    this.time = time;
  }
}
