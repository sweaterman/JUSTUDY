package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyResumeRepository extends JpaRepository<StudyResumeEntity,Long>, StudyResumeRepositorySupport {

}
