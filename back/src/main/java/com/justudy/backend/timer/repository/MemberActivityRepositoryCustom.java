package com.justudy.backend.timer.repository;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.List;

public interface MemberActivityRepositoryCustom {

  Tuple findTopTimeByYesterday(Date yesterday);

  Long findTimeByPeriodAndMember(Date ago, Date cur, MemberEntity member);

  List<ActivitySubjectResponse> findTimeByCategoryAndMember(MemberEntity member);

  Tuple findAllTimeByPeriod(Date ago, Date cur);

  List<ActivityCalendarResponse> findCalendarById(Date ago, Date cur, MemberEntity member);

  List<MemberActivityBeforeRank> sumTimeByPeriod(Date ago, Date cur);

}
