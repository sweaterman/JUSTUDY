package com.justudy.backend.study.repository;


import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;


public interface StudyResumeRepositorySupport {

    List<StudyResumeEntity> readAllStudyResumeByStudy(Long id);

    List<StudyResumeEntity> readAllStudyResumeByMember(Long id);
}
