package com.justudy.backend.member.repository;


import com.justudy.backend.member.domain.MemberFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<MemberFollow,Long>, FollowRepositoryCustom {
}
