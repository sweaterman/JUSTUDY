package com.justudy.backend.rank.common;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RankEnumMapper {

  private Map<String, List<RankEnumValue>> factory = new LinkedHashMap<>();

  private List<RankEnumValue> toEnumValues(Class<? extends RankEnumModel> e) {

    return Arrays
        .stream(e.getEnumConstants())
        .map(RankEnumValue::new)
        .collect(Collectors.toList());
  }

  public void put(String key, Class<? extends RankEnumModel> e) {
    factory.put(key, toEnumValues(e));
  }

  public Map<String, List<RankEnumValue>> getAll() {
    return factory;
  }

  public Map<String, List<RankEnumValue>> get(String keys) {

    return Arrays
        .stream(keys.split(","))
        .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
  }
}
