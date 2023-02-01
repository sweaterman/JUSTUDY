package com.justudy.backend.timer.dto.response;

import java.sql.Date;
import lombok.Data;

@Data
public class ActivityCalendarResponse {

  private String day;
  private Long second;

  public ActivityCalendarResponse(String day, Long second) {
    this.day = day;
    this.second = second;
  }


}
