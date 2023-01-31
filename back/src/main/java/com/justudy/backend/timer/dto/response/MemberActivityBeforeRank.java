package com.justudy.backend.timer.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.Data;

@Data
public class MemberActivityBeforeRank {

  private MemberEntity member;
  private Integer minute;


  public MemberActivityBeforeRank(MemberEntity member, Integer minute) {
    this.member = member;
    this.minute = minute;
  }


}
