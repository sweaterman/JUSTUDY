package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivityYesterdayResponse {

  String ninkName;
  Long second;

  public MemberActivityYesterdayResponse(String ninkName, Long second) {
    this.ninkName = ninkName;
    this.second = second;
  }
}
