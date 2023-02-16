package com.justudy.backend.timer.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.timer.domain.MemberActivityEntity;
import com.justudy.backend.timer.domain.QMemberActivityEntity;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import com.justudy.backend.timer.dto.response.MemberActivityBeforeRank;
import com.justudy.backend.timer.dto.response.MemberActivityYesterdayResponse;
import com.justudy.backend.timer.exception.NicknameNotFound;
import com.justudy.backend.timer.exception.YesterdayNoData;
import com.justudy.backend.timer.repository.MemberActivityRepository;
import com.querydsl.core.Tuple;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
  public void saveMemberAcitivity(ActivityRequest memberActivityRequest, Long seq,
      Date today) {
    MemberEntity member = memberRepository.getReferenceById(seq);
    Optional<Tuple> storeTime = memberActivityRepository.findTodayRecord(today,
        memberActivityRequest.getCategory(), member);

    if (storeTime.isEmpty()) {
      memberActivityRepository.save(MemberActivityEntity
          .builder()
          .member(member)
          .date(today)
          .time(memberActivityRequest.getSecond())
          .category(memberActivityRequest.getCategory())
          .build()
      );
    } else {
      Long sum = memberActivityRequest.getSecond() + storeTime.get().get(qMemberActivity.time);
      memberActivityRepository.save(MemberActivityEntity
          .builder()
          .sequence(storeTime.get().get(qMemberActivity.sequence))
          .member(member)
          .date(today)
          .time(sum)
          .category(memberActivityRequest.getCategory())
          .build()
      );

    }
  }

  @Transactional
  public MemberActivityYesterdayResponse getMemberActivityYesterdayTop(Date yesterday) {
    Tuple ret = memberActivityRepository.findTopTimeByYesterday(yesterday)
        .orElseThrow(() -> new YesterdayNoData());
    MemberEntity member = ret.get(qMemberActivity.member);
    Long second = ret.get(qMemberActivity.time.sum());

    return new MemberActivityYesterdayResponse(member.getNickname(), second);
  }

  @Transactional
  public Long getSumTimeByNickNameAndPeriod(Date ago, Date cur, String nickName) {
    Long userSeq = memberRepository.findSequenceByNickname(nickName)
        .orElseThrow(() -> new NicknameNotFound());
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      throw new NicknameNotFound();
    }
    Long sumTime = memberActivityRepository.findTimeByPeriodAndMember(ago, cur, member).orElse(0l);
    return sumTime;
  }

  @Transactional
  public List<ActivitySubjectResponse> getSumTimeByNickNameAndCategory(String nickName) {
    Long userSeq = memberRepository.findSequenceByNickname(nickName)
        .orElseThrow(() -> new NicknameNotFound());
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      throw new NicknameNotFound();
    }

    return memberActivityRepository.findTimeByCategoryAndMember(member);
  }


  @Transactional
  public Long getAvgTimeByPeriod(Date ago, Date cur) {
    Optional<Tuple> result = memberActivityRepository.findAllTimeByPeriod(ago, cur);
    if (result.isEmpty()) {
      return 0l;
    }

    Long count = result.get().get(qMemberActivity.member.countDistinct());
    Long sum = result.get().get(qMemberActivity.time.sum());

    Long sumTime = sum / count;

    return sumTime;
  }


  @Transactional
  public List<ActivityCalendarResponse> getCalendarTimeByNickName(Date ago, Date cur,
      String nickName) {
    Long userSeq = memberRepository.findSequenceByNickname(nickName)
        .orElseThrow(() -> new NicknameNotFound());
    MemberEntity member = memberRepository.getReferenceById(userSeq);
    if (member == null) {
      throw new NicknameNotFound();
    }
    return memberActivityRepository.findCalendarById(ago, cur, member);

  }


  @Transactional
  public List<ActivityToRank> getSumTimeByPeriod(Date ago, Date before) {
    List<MemberActivityBeforeRank> mabrList = memberActivityRepository.sumTimeByPeriod(ago, before);
    List<ActivityToRank> ret = IntStream.range(0, mabrList.size())
        .mapToObj(index -> dataConvert(index, mabrList.get(index))).collect(
            Collectors.toList());

    return ret;
  }


  private ActivityToRank dataConvert(int index, MemberActivityBeforeRank memberActivityBeforeRank) {
    MemberEntity member = memberActivityBeforeRank.getMember();

    Long image = member.getImageFile().getSequence();
    String name = member.getNickname();
    Long seq = member.getSequence();
    return new ActivityToRank(index + 1, memberActivityBeforeRank.getSecond(), name, image, seq);
  }

  @Transactional
  public List<ActivitySubjectResponse> getAllTimeByCategory() {
    return memberActivityRepository.sumAllTimeByCategory();
  }
}
