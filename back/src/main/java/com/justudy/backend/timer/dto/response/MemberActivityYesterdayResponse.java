package com.justudy.backend.timer.dto.response;

import lombok.Data;

@Data
public class MemberActivityYesterdayResponse {

  String nickName;
  Long second;

  public MemberActivityYesterdayResponse(String nickName, Long second) {
    this.nickName = nickName;
    this.second = second;
  }
}
