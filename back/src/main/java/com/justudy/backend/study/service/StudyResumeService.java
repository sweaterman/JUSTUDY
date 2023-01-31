package com.justudy.backend.study.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeRespond;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.request.StudyFrequencyEdit;
import com.justudy.backend.study.dto.request.StudyResumeCreate;
import com.justudy.backend.study.dto.response.StudyFrequencyResponse;
import com.justudy.backend.study.dto.response.StudyResumeResponse;
import com.justudy.backend.study.exception.StudyFrequencyNotFound;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import com.justudy.backend.study.repository.StudyRepository;
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
    private final int MAX_STUDY_PAGE_SIZE = 1;
    private final int MAX_NOTICE_SIZE = 3;

    public List<StudyResumeResponse> readAllResumeByMember(Long memberSeq) {
        studyRepository.findById(memberSeq)
                .orElseThrow(StudyNotFound::new);
        return studyResumeRepository.readAllStudyResumeByMember(studySequence)
                .stream()
                .map(StudyFrequencyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public Long createStudyResume(Long studySequence, StudyResumeCreate request) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        MemberEntity memberEntity = memberRepository.findById(request.getMember().getSequence())
                .orElseThrow(MemberNotFound::new);
        return studyResumeRepository.save(request.toEntity(studyEntity)).getSequence();
    }

    public StudyResumeResponse readStudyResume(Long resumeSeq) {
        StudyFrequencyEntity entity = studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(StudyFrequencyNotFound::new);
        return StudyResumeResponse.makeBuilder(entity);
    }

    public void deleteStudyResume(Long id) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyResumeRepository.deleteById(frequencySeq);
    }

    public List<StudyResumeResponse> readAllStudyResumeByStudy(Long id) {
    }
}


