package com.justudy.backend.timer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberActivityRequest {
  private String id;
  private Integer minute;
  private String category;
}
