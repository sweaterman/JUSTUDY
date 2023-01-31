package com.justudy.backend.timer.controller;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.timer.dto.request.MemberActivityRequest;
import com.justudy.backend.timer.dto.response.MemberActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.MemberActivitySubjectChange;
import com.justudy.backend.timer.dto.response.MemberActivityYesterdayResponse;
import com.justudy.backend.timer.service.MemberActivityService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/timer")
public class ActivityController {

  private final MemberActivityService memberActivityService;

  @PostMapping("/member")
  public ResponseEntity<Void> registerPersonalTime(HttpSession session,
      @RequestBody MemberActivityRequest memberActivityRequest) {

    log.info("registerPersonalTime {} ", memberActivityRequest);
    Long seq = (Long) session.getAttribute(SessionConst.LOGIN_USER);
    Date todayDate = Date.valueOf(LocalDate.now());

    memberActivityService.saveMemberAcitivity(memberActivityRequest, seq, todayDate);

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @GetMapping("/member/yesterday-top")
  public ResponseEntity<MemberActivityYesterdayResponse> readYesterdayTop() {
    Date yesterdayDate = Date.valueOf(LocalDate.now().minusDays(1));
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getMemberActivityYesterdayTop(yesterdayDate));
  }

  @GetMapping("/member/week")
  public ResponseEntity<HashMap> readWeekTimeBySeq(@RequestParam Long seq) {
    HashMap<String, String> ret = new HashMap<String, String>();

    Date agoWeek = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curWeek = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getSumTimeByIdAndPeriod(agoWeek, curWeek, seq));
    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/member/month")
  public ResponseEntity<HashMap> readMonthTimeBySeq(@RequestParam Long seq) {
    HashMap<String, String> ret = new HashMap<String, String>();

    Date agoMonth = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curMonth = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getSumTimeByIdAndPeriod(agoMonth, curMonth, seq));
    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/member/category")
  public ResponseEntity<List<MemberActivitySubjectChange>> readCategoryTimeBySeq(
      @RequestParam Long seq) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getSumTimeByIdAndCategory(seq));
  }

  @GetMapping("/members/week")
  public ResponseEntity<HashMap> readWeekTimeAvg() {
    HashMap<String, String> ret = new HashMap<String, String>();

    Date agoWeek = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curWeek = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getAvgTimeByPeriod(agoWeek, curWeek));

    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/members/month")
  public ResponseEntity<HashMap> readMonthTimeAvg() {
    HashMap<String, String> ret = new HashMap<String, String>();

    Date agoMonth = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curMonth = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getAvgTimeByPeriod(agoMonth, curMonth));

    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/member-calendar")
  public ResponseEntity<List<MemberActivityCalendarResponse>> readCalendarTimeBySeq(
      @RequestParam Long seq) {
    LocalDate today = LocalDate.now();
    LocalDate startDay = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
    Date firstDay = Date.valueOf(startDay);
    Date lastDay = Date.valueOf(today);

    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getCalendarTimeById(firstDay, lastDay, seq));
  }


}
