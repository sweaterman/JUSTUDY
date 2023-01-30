package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity,Long>, StudyRepositorySupport {

}
