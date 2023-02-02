package com.justudy.backend.study.service;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.StudyMemberNotFound;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyMemberRepository;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyMemberService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void updateStudyLeader(Long id, Long memberId) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        //스터디 리더 변경
        entity.changeLeader(memberId);
    }

    public List<StudyResponse> readAllRegisterStudy(Long id) {
        //가입한 스터디 찾기
        List<StudyMemberEntity> studyMemberEntities = studyMemberRepository.readAllRegisterStudy(id);
        //가입한 번호의 스터디 리스트 출력
        return studyMemberEntities
                .stream()
                .map(StudyMemberEntity::getStudy)
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    public void exileStudyMember(Long id, Long memberId) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        //todo session 아이디와 리더 비교해서 아닐시 오류 권한이 없습니다
        //todo 스터디원인지 확인
        StudyMemberEntity memberEntity = checkStudyMember(studyEntity.getStudyMembers(), memberId);
        log.info("정보5 {},{},{}",memberEntity.getStudy().getSequence(),memberEntity.getMember().getSequence(),memberEntity.getSequence());
        studyEntity.getLeaderSeq();

        //스터디원 추방
        studyMemberRepository.deleteStudyMember(id, memberId);
//        studyMemberRepository.deleteById(memberEntity.getSequence());
//        studyMemberRepository.delete(memberEntity);
        log.info("정보8 {},{},{}",memberEntity.getStudy().getSequence(),memberEntity.getMember().getSequence(),memberEntity.getSequence());

    }

    @Transactional
    public void withdrawStudyMember(Long id, Long memberId) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        //todo session id와 리더 비교해서 아닐시 오류 권한이 없습니다
        //스터디원인지 확인
        StudyMemberEntity MemberEntity = checkStudyMember(studyEntity.getStudyMembers(), memberId);
        //memberid와 리더seq비교 아닐시 invalid request error
        Long leaderSeq = studyEntity.getLeaderSeq();
        if (checkLeader(leaderSeq, memberId)) throw new InvalidRequest("memberId", "리더는 탈퇴할 수 없습니다");
        //스터디 탈퇴
        studyMemberRepository.deleteStudyMember(id, memberId);
    }

    @Transactional
    public Long createStudyMember(StudyMemberCreate request) {
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq()).orElseThrow(MemberNotFound::new);
        StudyEntity studyEntity = studyRepository.findById(request.getStudySeq()).orElseThrow(StudyNotFound::new);

        return studyMemberRepository.save(request.toEntity(memberEntity, studyEntity)).getSequence();
    }

    @Transactional
    public void deleteStudyMemberByStudy(Long studySequence) {
        studyMemberRepository.deleteStudyMemberByStudy(studySequence);

    }

    private boolean checkLeader(Long leaderSeq, Long memberId) {
        if (leaderSeq.longValue() == memberId.longValue()) return true;
        return false;
    }

    private StudyMemberEntity checkStudyMember(List<StudyMemberEntity> studyMembers, Long memberId) {
        log.info("정보4 : {}",studyMembers.size());
        return studyMembers
                .stream()
                .filter(studyMemberEntity -> studyMemberEntity.getMember().getSequence().equals(memberId))
                .findFirst()
                .orElseThrow(StudyMemberNotFound::new);
    }

}
