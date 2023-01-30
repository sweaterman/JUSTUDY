package com.justudy.backend.timer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberActivityRequest {

  private Integer minute;
  private String category;
}
