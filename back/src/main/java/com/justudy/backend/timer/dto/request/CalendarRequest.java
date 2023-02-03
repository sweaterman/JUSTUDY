package com.justudy.backend.timer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalendarRequest {

  private Long seq;
  private Integer year;
  private Integer month;
}
