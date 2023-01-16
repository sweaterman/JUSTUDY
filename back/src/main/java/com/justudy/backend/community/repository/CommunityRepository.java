package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity,Long>,CommunityRepositorySupport{

}
