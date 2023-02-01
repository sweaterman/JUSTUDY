package com.justudy.backend.study.repository;


import com.justudy.backend.study.domain.StudyFrequencyEntity;

import java.util.List;


public interface StudyFrequencyRepositorySupport {

    List<StudyFrequencyEntity> readAllStudyFrequency(Long studySequence);

    void deleteByStudy(Long studyId);
}
