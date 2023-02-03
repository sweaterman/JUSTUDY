package com.justudy.backend.study.repository;


import com.justudy.backend.study.domain.StudyResumeEntity;

import java.util.List;


public interface StudyResumeRepositorySupport {

    List<StudyResumeEntity> readAllStudyResumeByStudy(Long id);

    List<StudyResumeEntity> readAllStudyResumeByMember(Long id);
}
