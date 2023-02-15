package com.justudy.backend.study.repository;


import com.justudy.backend.study.domain.StudyResumeEntity;

import java.util.List;
import java.util.Optional;


public interface StudyResumeRepositorySupport {

    List<StudyResumeEntity> readAllStudyResumeByStudy(Long id);

    List<StudyResumeEntity> readAllStudyResumeByMember(Long id);

    Optional<StudyResumeEntity> readStudyResumeByStudyAndMember(Long sequence, Long loginSequence);

    void deleteStudyResumeByStudy(Long id);
}
