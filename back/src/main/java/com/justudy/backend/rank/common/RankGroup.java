package com.justudy.backend.rank.common;

import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.function.Function;

public enum RankGroup implements RankEnumModel {
  PERSON("개인"  ),
  GROUP("단체");
  private final String value;

  RankGroup(String value) {
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
