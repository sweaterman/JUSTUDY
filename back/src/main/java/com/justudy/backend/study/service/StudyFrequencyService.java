package com.justudy.backend.study.service;

import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.request.StudyFrequencyEdit;
import com.justudy.backend.study.dto.response.StudyFrequencyResponse;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.exception.StudyFrequencyNotFound;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyFrequencyService {

    private final StudyRepository studyRepository;
    private final StudyFrequencyRepository studyFrequencyRepository;
    private final int MAX_STUDY_PAGE_SIZE = 1;
    private final int MAX_NOTICE_SIZE = 3;

    public List<StudyFrequencyResponse> readAllStudyFrequency(Long studySequence) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return studyFrequencyRepository.readAllStudyFrequency(studySequence)
                .stream()
                .map(StudyFrequencyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional

    public Long createStudyFrequency(Long studySequence, StudyFrequencyCreate request) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        Long studyFrequencySeq = studyFrequencyRepository.save(request.toEntity(studyEntity)).getSequence();
        return studyFrequencySeq;

    }

    @Transactional
    public Long updateStudyFrequency(Long studySequence, Long frequencySeq, StudyFrequencyEdit request) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        StudyFrequencyEntity studyFrequencyEntity = studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(StudyFrequencyNotFound::new);
        studyFrequencyEntity.update(studyEntity, request.getWeek(), request.getStartTime(), request.getEndTime());
        return frequencySeq;
    }

    @Transactional
    public void deleteStudyFrequency(Long studySequence, Long frequencySeq) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyFrequencyRepository.deleteById(frequencySeq);
    }


    public StudyFrequencyResponse readStudyFrequency(Long frequencySeq) {
        StudyFrequencyEntity entity = studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(StudyFrequencyNotFound::new);
        return StudyFrequencyResponse.makeBuilder(entity);
    }

    @Transactional
    public void deleteStudyFrequencyByStudy(Long studySequence) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyFrequencyRepository.deleteByStudy(studySequence);
    }
}


