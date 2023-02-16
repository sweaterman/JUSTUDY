package com.justudy.backend.study.service;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyFrequencyWeek;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.request.StudyFrequencyEdit;
import com.justudy.backend.study.dto.response.StudyFrequencyResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.exception.StudyFrequencyNotFound;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyFrequencyService {

    private final StudyRepository studyRepository;
    private final StudyFrequencyRepository studyFrequencyRepository;

    public List<StudyFrequencyResponse> readAllStudyFrequency(Long studySequence) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return studyFrequencyRepository.readAllStudyFrequency(studySequence)
                .stream()
                .map(StudyFrequencyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional

    public Long createStudyFrequency(Long studySequence, StudyFrequencyCreate request) throws ParseException {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        StudyFrequencyEntity studyFrequencyEntity = studyFrequencyRepository.save(request.toEntity(studyEntity));
        studyEntity.addStudyFrequency(studyFrequencyEntity);
        return studyFrequencyEntity.getSequence();
    }

    @Transactional
    public Long updateStudyFrequency(Long studySequence, Long frequencySeq, StudyFrequencyEdit request) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        StudyFrequencyEntity studyFrequencyEntity = studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(StudyFrequencyNotFound::new);
        studyFrequencyEntity.update(studyEntity, StudyFrequencyWeek.valueOf(request.getWeek()), request.getStartTime(), request.getEndTime());
        return frequencySeq;
    }

    @Transactional
    public void deleteStudyFrequency(Long studySequence, Long frequencySeq) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyEntity.removeStudyFrequency(studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(InvalidRequest::new));
        studyFrequencyRepository.deleteById(frequencySeq);
    }


    public StudyFrequencyResponse readStudyFrequency(Long frequencySeq) {
        StudyFrequencyEntity entity = studyFrequencyRepository.findById(frequencySeq)
                .orElseThrow(StudyFrequencyNotFound::new);
        return StudyFrequencyResponse.makeBuilder(entity);
    }

    @Transactional
    public void deleteStudyFrequencyByStudy(Long studySequence) {
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        //ConcurrentModificationException을 피하기위해 iterator를 활용한 제거
//        for (Iterator<StudyFrequencyEntity> iterator = studyEntity.getFrequency().iterator(); iterator.hasNext(); ) {
//            iterator.next().changeStudy(null);
//            iterator.remove();
//        }
        log.info("loo {}",studySequence);
        studyFrequencyRepository.deleteByStudy(studySequence);
    }

    public void createStudyFrequencies(Long studySequence, List<StudyFrequencyCreate> frequencies) throws ParseException {
        if (frequencies == null || frequencies.isEmpty()) return;
        StudyEntity studyEntity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        List<StudyFrequencyEntity> studyFrequencyEntities = new ArrayList<>();
        for (StudyFrequencyCreate studyFrequencyCreate : frequencies) {
            studyFrequencyEntities.add(studyFrequencyCreate.toEntity(studyEntity));
        }
        studyFrequencyRepository.saveAll(studyFrequencyEntities).forEach(studyEntity::addStudyFrequency);
    }
}


