package com.justudy.backend.timer.repository;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.timer.dto.response.MemberActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.MemberActivitySubjectResponse;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.List;

public interface MemberActivityRepositoryCustom {

  Tuple findTopTimeByYesterday(Date yesterday);

  Integer findTimeByPeriodAndMember(Date ago, Date cur, MemberEntity member);

  List<MemberActivitySubjectResponse> findTimeByCategoryAndMember(MemberEntity member);

  Tuple findAllTimeByPeriod(Date ago, Date cur);

  List<MemberActivityCalendarResponse> findCalendarById(Date ago, Date cur, MemberEntity member);

}
