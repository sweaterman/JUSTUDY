package com.justudy.backend.timer.controller;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.dto.request.CalendarRequest;
import com.justudy.backend.timer.dto.request.MemberCalendarRequest;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityYesterdayResponse;
import com.justudy.backend.timer.service.MemberActivityService;
import com.justudy.backend.timer.service.RoomActivityService;
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
  private final RoomActivityService roomActivityService;

  @PostMapping("/member")
  public ResponseEntity<Void> registerPersonalTime(HttpSession session,
      @RequestBody ActivityRequest memberActivityRequest) {

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
  public ResponseEntity<HashMap> readWeekTimeBySeq(@RequestParam String nickName) {
    HashMap<String, Long> ret = new HashMap<String, Long>();

    Date agoWeek = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curWeek = Date.valueOf(LocalDate.now());
    ret.put("time",
        memberActivityService.getSumTimeByNickNameAndPeriod(agoWeek, curWeek, nickName));
    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/member/month")
  public ResponseEntity<HashMap> readMonthTimeBySeq(@RequestParam String nickName) {
    HashMap<String, Long> ret = new HashMap<String, Long>();

    Date agoMonth = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curMonth = Date.valueOf(LocalDate.now());
    ret.put("time",
        memberActivityService.getSumTimeByNickNameAndPeriod(agoMonth, curMonth, nickName));
    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/member/category")
  public ResponseEntity<List<ActivitySubjectResponse>> readCategoryTimeBySeq(
      @RequestParam String nickName) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getSumTimeByNickNameAndCategory(nickName));
  }

  @GetMapping("/member/all-category")
  public ResponseEntity<List<ActivitySubjectResponse>> readMemberCategoryAllTime() {

    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getAllTimeByCategory());
  }

  @GetMapping("/members/week")
  public ResponseEntity<HashMap> readWeekTimeAvg() {
    HashMap<String, Long> ret = new HashMap<String, Long>();

    Date agoWeek = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curWeek = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getAvgTimeByPeriod(agoWeek, curWeek));

    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @GetMapping("/members/month")
  public ResponseEntity<HashMap> readMonthTimeAvg() {
    HashMap<String, Long> ret = new HashMap<String, Long>();

    Date agoMonth = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curMonth = Date.valueOf(LocalDate.now());
    ret.put("time", memberActivityService.getAvgTimeByPeriod(agoMonth, curMonth));

    return ResponseEntity.status(HttpStatus.OK).body(ret);
  }

  @PostMapping("/member-calendar")
  public ResponseEntity<List<ActivityCalendarResponse>> readCalendarTimeBySeq(
      @RequestBody MemberCalendarRequest memberCalendarRequest) {
    LocalDate wantedMonth = LocalDate.of(memberCalendarRequest.getYear(),
        memberCalendarRequest.getMonth(), 1);
    Date firstDay = Date.valueOf(wantedMonth.withDayOfMonth(1));
    Date lastDay = Date.valueOf(wantedMonth.withDayOfMonth(wantedMonth.lengthOfMonth()));

    return ResponseEntity.status(HttpStatus.OK)
        .body(memberActivityService.getCalendarTimeByNickName(firstDay, lastDay,
            memberCalendarRequest.getNickName()));
  }

  @PostMapping("/study-calendar")
  public ResponseEntity<List<ActivityCalendarResponse>> readStudyCalendarTimeBySeq(
      @RequestBody CalendarRequest roomCalendarRequest) {
    LocalDate wantedMonth = LocalDate.of(roomCalendarRequest.getYear(),
        roomCalendarRequest.getMonth(), 1);
    Date firstDay = Date.valueOf(wantedMonth.withDayOfMonth(1));
    Date lastDay = Date.valueOf(wantedMonth.withDayOfMonth(wantedMonth.lengthOfMonth()));

    return ResponseEntity.status(HttpStatus.OK)
        .body(roomActivityService.getCalendarTimeById(firstDay, lastDay,
            roomCalendarRequest.getSeq()));
  }

  @GetMapping("/study/all-category")
  public ResponseEntity<List<ActivitySubjectResponse>> readStudyCategoryAllTime() {

    return ResponseEntity.status(HttpStatus.OK)
        .body(roomActivityService.getAllTimeByCategory());
  }
}
