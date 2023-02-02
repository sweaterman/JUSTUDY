package com.justudy.backend.rank.controller;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.service.RankService;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import com.justudy.backend.timer.service.MemberActivityService;
import com.justudy.backend.timer.service.RoomActivityService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RankDummy {

  private final RankService rankService;
  private final MemberActivityService memberActivityService;
  private final RoomActivityService roomActivityService;


  //  @Scheduled(cron = "30 * * * * *")//초 분 시 일 월 요일

  public void renewalPersonYesterdayRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusDays(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.PERSON, RankType.YESTERDAY);
    List<ActivityToRank> readData = memberActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.PERSON, RankType.YESTERDAY, readData);
    }
  }

  public void renewalPersonWeekRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.PERSON, RankType.WEEK);
    List<ActivityToRank> readData = memberActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.PERSON, RankType.WEEK, readData);
    }

  }

  public void renewalPersonMonthRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.PERSON, RankType.MONTH);
    List<ActivityToRank> readData = memberActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.PERSON, RankType.MONTH, readData);
    }

  }

  public void renewalStudyYesterdayRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusDays(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.GROUP, RankType.YESTERDAY);
    List<ActivityToRank> readData = roomActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.GROUP, RankType.YESTERDAY, readData);
    }

  }

  public void renewalStudyWeekRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusWeeks(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.GROUP, RankType.WEEK);
    List<ActivityToRank> readData = roomActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.GROUP, RankType.WEEK, readData);
    }

  }

  public void renewalStudyMonthRank() {
    Date agoDay = Date.valueOf(LocalDate.now().minusMonths(1));
    Date curDay = Date.valueOf(LocalDate.now());

    rankService.deleteAllByGroupAndType(RankGroup.GROUP, RankType.MONTH);
    List<ActivityToRank> readData = roomActivityService.getSumTimeByPeriod(agoDay, curDay);
    if (readData != null) {
      rankService.saveRank(RankGroup.GROUP, RankType.MONTH, readData);
    }

  }
}
