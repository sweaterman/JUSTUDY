package com.justudy.backend.rank.common;

import com.justudy.backend.common.enum_util.EnumMapper;
import com.justudy.backend.common.enum_util.EnumValue;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RankEnumController {

  private final RankEnumMapper rankEnumMapper;

  @GetMapping("/rank-enum")
  public Map<String, List<RankEnumValue>> getEnumValue() {
    return rankEnumMapper.getAll();
  }
}
