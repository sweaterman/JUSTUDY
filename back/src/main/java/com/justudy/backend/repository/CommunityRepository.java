package com.justudy.backend.repository;

import com.justudy.backend.domain.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity,Long>,CommunityRepositorySupport{

}
