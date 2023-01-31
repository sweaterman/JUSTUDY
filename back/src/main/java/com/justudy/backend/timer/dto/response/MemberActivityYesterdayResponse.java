package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivityYesterdayResponse {

  String ninkName;
  String time;

  public MemberActivityYesterdayResponse(String ninkName, String time) {
    this.ninkName = ninkName;
    this.time = time;
  }
}
