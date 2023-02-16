package com.justudy.backend.study.service;

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

import java.util.Iterator;
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
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        //스터디원인지 확인
        StudyMemberEntity studyMemberEntity = checkStudyMember(studyEntity.getStudyMembers(), memberId);
        //스터디 리더 변경
        studyEntity.changeLeader(memberId);
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

        //스터디원인지 확인
        StudyMemberEntity memberEntity = checkStudyMember(studyEntity.getStudyMembers(), memberId);

        //스터디원 추방
        studyEntity.removeStudyMember(memberEntity);
        studyMemberRepository.deleteById(memberEntity.getSequence());
    }

    @Transactional
    public void withdrawStudyMember(Long id, Long memberId) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        //스터디원인지 확인
        StudyMemberEntity memberEntity = checkStudyMember(studyEntity.getStudyMembers(), memberId);

        //스터디 탈퇴
        studyEntity.removeStudyMember(memberEntity);
        studyMemberRepository.deleteStudyMember(id, memberId);
    }

    @Transactional
    public Long createStudyMember(StudyMemberCreate request) {
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq()).orElseThrow(MemberNotFound::new);
        StudyEntity studyEntity = studyRepository.findById(request.getStudySeq()).orElseThrow(StudyNotFound::new);
        StudyMemberEntity studyMemberEntity = studyMemberRepository.save(request.toEntity(memberEntity, studyEntity));
        studyEntity.addStudyMember(studyMemberEntity);
        return studyMemberEntity.getSequence();
    }

    @Transactional
    public void deleteStudyMemberByStudy(Long studySequence) {
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
//        List<StudyMemberEntity> studyMemberEntities = studyMemberRepository.readAllByStudy(studySequence);

        //ConcurrentModificationException을 피하기위해 iterator를 활용한 제거
//        for (Iterator<StudyMemberEntity> iterator = studyEntity.getStudyMembers().iterator(); iterator.hasNext(); ) {
//            iterator.next().changeStudy(null);
//            iterator.remove();
//        }
        studyMemberRepository.deleteStudyMemberByStudy(studySequence);
    }



    private StudyMemberEntity checkStudyMember(List<StudyMemberEntity> studyMembers, Long memberId) {
        return studyMembers
                .stream()
                .filter(studyMemberEntity -> studyMemberEntity.getMember().getSequence().equals(memberId))
                .findFirst()
                .orElseThrow(StudyMemberNotFound::new);
    }
}
