package com.justudy.backend.rank.repository;

import com.justudy.backend.rank.domain.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<RankEntity, Long>, RankRepositoryCustom {

}
