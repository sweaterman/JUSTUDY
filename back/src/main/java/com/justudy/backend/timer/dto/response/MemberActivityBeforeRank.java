package com.justudy.backend.timer.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.Data;

@Data
public class MemberActivityBeforeRank {

  private MemberEntity member;
  private Long second;


  public MemberActivityBeforeRank(MemberEntity member, Long second) {
    this.member = member;
    this.second = second;
  }


}
