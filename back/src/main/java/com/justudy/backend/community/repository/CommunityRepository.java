package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity,Long>, CommunityRepositoryCustom {

}
