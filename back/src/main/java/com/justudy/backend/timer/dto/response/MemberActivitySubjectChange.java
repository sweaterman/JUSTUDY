package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivitySubjectChange {

  private String category;
  private String time;

  public MemberActivitySubjectChange(String category, String time) {
    this.category = category;
    this.time = time;
  }
}
