package com.justudy.backend.timer.dto.response;

import java.sql.Date;
import lombok.Data;

@Data
public class MemberActivityCalendarResponse {

  private String day;
  private Integer minute;

  public MemberActivityCalendarResponse(String day, Integer minute) {
    this.day = day;
    this.minute = minute;
  }


}
