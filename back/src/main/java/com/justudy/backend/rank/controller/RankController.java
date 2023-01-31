package com.justudy.backend.rank.controller;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.dto.response.RankResponse;
import com.justudy.backend.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rank")
public class RankController {

  private final RankService rankService;

  @GetMapping("/members/yesterday")
  public ResponseEntity<List<RankResponse>> readAllMembersYesterday() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.PERSON, RankType.YESTERDAY));
  }

  @GetMapping("/members/week")
  public ResponseEntity<List<RankResponse>> readAllMembersWeek() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.PERSON, RankType.WEEK));
  }

  @GetMapping("/members/month")
  public ResponseEntity<List<RankResponse>> readAllMembersMonth() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.PERSON, RankType.MONTH));
  }

  @GetMapping("/study/yesterday")
  public ResponseEntity<List<RankResponse>> readAllGroupYesterday() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.GROUP, RankType.YESTERDAY));
  }

  @GetMapping("/study/week")
  public ResponseEntity<List<RankResponse>> readAllGroupWeek() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.GROUP, RankType.WEEK));
  }

  @GetMapping("/study/month")
  public ResponseEntity<List<RankResponse>> readAllGroupMonth() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rankService.readAllByGroupAndType(RankGroup.GROUP, RankType.MONTH));
  }
}
