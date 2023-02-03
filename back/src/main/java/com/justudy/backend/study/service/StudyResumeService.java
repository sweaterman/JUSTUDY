package com.justudy.backend.study.service;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.dto.request.StudyResumeCreate;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.dto.response.StudyResumeResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.exception.StudyResumeNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.StudyResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyResumeService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyResumeRepository studyResumeRepository;

    @Transactional
    public Long createStudyResume(Long studySequence, StudyResumeCreate request) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq())
                .orElseThrow(MemberNotFound::new);
        //todo 같은 스터디 이미 신청했는지 확인

        StudyResumeEntity studyResumeEntity = studyResumeRepository.save(request.toEntity(studyEntity, memberEntity));
        studyEntity.addStudyResume(studyResumeEntity);
        return studyResumeEntity.getSequence();
    }

    @Transactional
    public void deleteStudyResume(Long id) {
        StudyResumeEntity studyResumeEntity = studyResumeRepository.findById(id).orElseThrow(InvalidRequest::new);
        StudyEntity studyEntity = studyRepository.findById(studyResumeEntity.getStudy().getSequence())
                .orElseThrow(StudyNotFound::new);
        studyEntity.removeStudyResume(studyResumeEntity);
        studyResumeRepository.deleteById(id);
    }

    public StudyResumeResponse readStudyResume(Long resumeSeq) {
        StudyResumeEntity entity = studyResumeRepository.findById(resumeSeq)
                .orElseThrow(StudyResumeNotFound::new);
        return StudyResumeResponse.makeBuilder(entity);
    }


    public List<StudyResumeResponse> readAllStudyResumeByStudy(Long id) {
        studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        return studyResumeRepository.readAllStudyResumeByStudy(id)
                .stream()
                .map(StudyResumeResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<StudyResponse> readAllApplyStudy(Long id) {
        return studyResumeRepository.readAllStudyResumeByMember(id)
                .stream()
                .map(StudyResumeEntity::getStudy)
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }
}


