package com.justudy.backend.timer.repository;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MemberActivityRepositoryCustom {

  Optional<Tuple> findTodayRecord(Date date, String category, MemberEntity member);

  Optional<Tuple> findTopTimeByYesterday(Date yesterday);

  Optional<Long> findTimeByPeriodAndMember(Date ago, Date cur, MemberEntity member);

  List<ActivitySubjectResponse> findTimeByCategoryAndMember(MemberEntity member);

  Optional<Tuple> findAllTimeByPeriod(Date ago, Date cur);

  List<ActivityCalendarResponse> findCalendarById(Date ago, Date cur, MemberEntity member);

  List<MemberActivityBeforeRank> sumTimeByPeriod(Date ago, Date cur);

  List<ActivitySubjectResponse> sumAllTimeByCategory();
}
