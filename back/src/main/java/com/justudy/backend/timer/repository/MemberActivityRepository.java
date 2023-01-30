package com.justudy.backend.timer.repository;

import com.justudy.backend.timer.domain.MemberActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberActivityRepository extends JpaRepository<MemberActivityEntity, Long>,
    MemberActivityRepositoryCustom {

}
