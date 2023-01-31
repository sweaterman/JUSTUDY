package com.justudy.backend.timer.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.timer.domain.MemberActivityEntity;
import com.justudy.backend.timer.domain.QMemberActivityEntity;
import com.justudy.backend.timer.dto.request.MemberActivityRequest;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.justudy.backend.timer.dto.response.MemberActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.MemberActivitySubjectChange;
import com.justudy.backend.timer.dto.response.MemberActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.MemberActivityToRank;
import com.justudy.backend.timer.dto.response.MemberActivityYesterdayResponse;
import com.justudy.backend.timer.repository.MemberActivityRepository;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberActivityService {

  private final MemberRepository memberRepository;
  private final MemberActivityRepository memberActivityRepository;
  private final QMemberActivityEntity qMemberActivity = QMemberActivityEntity.memberActivityEntity;

  @Transactional
  public void saveMemberAcitivity(MemberActivityRequest memberActivityRequest, Long seq,
      Date today) {
    MemberEntity member = memberRepository.getReferenceById(seq);
    memberActivityRepository.save(MemberActivityEntity
        .builder()
        .member(member)
        .date(today)
        .time(memberActivityRequest.getMinute())
        .category(memberActivityRequest.getCategory())
        .build()
    );
  }

  @Transactional
  public MemberActivityYesterdayResponse getMemberActivityYesterdayTop(Date yesterday) {
    Tuple ret = memberActivityRepository.findTopTimeByYesterday(yesterday);
    if (ret == null) {
      log.info("findTopTimeByYesterday null");
      return null;//에러 페이지 넣기
    }
    MemberEntity member = ret.get(qMemberActivity.member);
    Integer minute = ret.get(qMemberActivity.time.sum());
    String strMinute = new StringBuilder(String.format("%02d", minute / 60)).append(":")
        .append(String.format("%02d", minute % 60)).toString();

    return new MemberActivityYesterdayResponse(member.getNickname(), strMinute);
  }

  @Transactional
  public String getSumTimeByIdAndPeriod(Date ago, Date cur, Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }
    Integer sumTime = memberActivityRepository.findTimeByPeriodAndMember(ago, cur, member);
    if (sumTime == null) {
      sumTime = 0;
    }
    return new StringBuilder(String.format("%02d", sumTime / 1440)).append(":")
        .append(String.format("%02d", (sumTime % 1440) / 24)).append(":")
        .append(String.format("%02d", sumTime % 60)).toString();
  }

  @Transactional
  public List<MemberActivitySubjectChange> getSumTimeByIdAndCategory(Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }

    return memberActivityRepository.findTimeByCategoryAndMember(member).stream()
        .map(MemberActivitySubjectResponse::changeData)
        .collect(Collectors.toList());
  }


  @Transactional
  public String getAvgTimeByPeriod(Date ago, Date cur) {
    Tuple result = memberActivityRepository.findAllTimeByPeriod(ago, cur);
    if (result == null) {
      return null;//에러페이지 넣기
    }
    Integer count = (int) (long) result.get(qMemberActivity.member.countDistinct());
    Integer sum = result.get(qMemberActivity.time.sum());

    Integer sumTime = sum / count;

    return new StringBuilder(String.format("%02d", sumTime / 1440)).append(":")
        .append(String.format("%02d", (sumTime % 1440) / 24)).append(":")
        .append(String.format("%02d", sumTime % 60)).toString();
  }


  @Transactional
  public List<MemberActivityCalendarResponse> getCalendarTimeById(Date ago, Date cur,
      Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }

    return memberActivityRepository.findCalendarById(ago, cur, member);

  }


  @Transactional
  public List<MemberActivityToRank> getSumTimeByPeriod(Date ago, Date before) {
    List<MemberActivityBeforeRank> mabrList = memberActivityRepository.sumTimeByPeriod(ago, before);
    List<MemberActivityToRank> ret = new LinkedList<MemberActivityToRank>();

    log.info("getSumTimeByPeriod start!!!");
    for (int index = 0; index < mabrList.size(); index++) {
      MemberActivityBeforeRank mabr = mabrList.get(index);

      Long image = mabr.getMember().getImageFile().getSequence();
      Integer order = index + 1;
      String name = mabr.getMember().getNickname();
      Integer sumTime = mabr.getMinute();
      String time = new StringBuilder(String.format("%02d", sumTime / 1440)).append(":")
          .append(String.format("%02d", (sumTime % 1440) / 24)).append(":")
          .append(String.format("%02d", sumTime % 60)).toString();

      ret.add(new MemberActivityToRank(order,time,name,image));
    }
    return ret;
  }
}
