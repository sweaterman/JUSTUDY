package com.justudy.backend.rank.common;

public enum RankType implements RankEnumModel {
  YESTERDAY("어제"),
  WEEK("이번주"),
  MONTH("이번달");
  private final String value;

  RankType(String value) {
    this.value = value;
  }

  @Override
  public String getKey() {
    return name();
  }

  @Override
  public String getValue() {
    return this.value;
  }
}
