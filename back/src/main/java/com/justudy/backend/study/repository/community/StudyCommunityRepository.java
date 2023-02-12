package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCommunityRepository extends JpaRepository<StudyCommunityEntity,Long>, StudyCommunityRepositoryCustom {

}
