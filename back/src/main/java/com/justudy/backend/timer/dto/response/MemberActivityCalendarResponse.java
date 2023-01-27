package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivityCalendarResponse {

  private Integer day;
  private Integer minute;

  public MemberActivityCalendarResponse(Integer day, Integer minute) {
    this.day = day;
    this.minute = minute;
  }
}
