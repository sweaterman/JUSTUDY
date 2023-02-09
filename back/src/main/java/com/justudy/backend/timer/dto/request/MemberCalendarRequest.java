package com.justudy.backend.timer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCalendarRequest {

  private String nickName;
  private Integer year;
  private Integer month;
}
