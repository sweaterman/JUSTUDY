package com.justudy.backend.rank.common;

import lombok.Getter;

@Getter
public class RankEnumValue {

  private String key;

  private String value;

  public RankEnumValue(RankEnumModel rankEnumModel) {
    key = rankEnumModel.getKey();
    value = rankEnumModel.getValue();
  }
}
