package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyFrequencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFrequencyRepository extends JpaRepository<StudyFrequencyEntity,Long>, StudyFrequencyRepositorySupport {

}
