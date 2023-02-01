package com.justudy.backend.timer.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.timer.domain.MemberActivityEntity;
import com.justudy.backend.timer.domain.QMemberActivityEntity;
import com.justudy.backend.timer.dto.request.MemberActivityRequest;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.justudy.backend.timer.dto.response.MemberActivityYesterdayResponse;
import com.justudy.backend.timer.repository.MemberActivityRepository;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
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
        .time(memberActivityRequest.getSecond())
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
    Long second = ret.get(qMemberActivity.time.sum());
    return new MemberActivityYesterdayResponse(member.getNickname(), second);
  }

  @Transactional
  public Long getSumTimeByIdAndPeriod(Date ago, Date cur, Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }
    Long sumTime = memberActivityRepository.findTimeByPeriodAndMember(ago, cur, member);
    if (sumTime == null) {
      sumTime = 0l;
    }
    return sumTime;
  }

  @Transactional
  public List<ActivitySubjectResponse> getSumTimeByIdAndCategory(Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }

    return memberActivityRepository.findTimeByCategoryAndMember(member);
  }


  @Transactional
  public Long getAvgTimeByPeriod(Date ago, Date cur) {
    Tuple result = memberActivityRepository.findAllTimeByPeriod(ago, cur);
    if (result == null) {
      return null;//에러페이지 넣기
    }
    Long count = result.get(qMemberActivity.member.countDistinct());
    Long sum =result.get(qMemberActivity.time.sum());

    Long sumTime = sum / count;

    return sumTime;
  }


  @Transactional
  public List<ActivityCalendarResponse> getCalendarTimeById(Date ago, Date cur,
      Long userSeq) {
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      return null;//에러 페이지 넣기
    }

    return memberActivityRepository.findCalendarById(ago, cur, member);

  }


  @Transactional
  public List<ActivityToRank> getSumTimeByPeriod(Date ago, Date before) {
    List<MemberActivityBeforeRank> mabrList = memberActivityRepository.sumTimeByPeriod(ago, before);
    List<ActivityToRank> ret = new LinkedList<ActivityToRank>();

    for (int index = 0; index < mabrList.size(); index++) {
      MemberActivityBeforeRank mabr = mabrList.get(index);

      Long image = mabr.getMember().getImageFile().getSequence();
      Integer order = index + 1;
      String name = mabr.getMember().getNickname();
      Long sumTime = mabr.getSecond();

      ret.add(new ActivityToRank(order, sumTime, name, image));
    }
    return ret;
  }
}
