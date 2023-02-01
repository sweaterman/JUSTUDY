package com.justudy.backend.timer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class MemberActivityRequest {

  private Long second;
  private String category;
}
