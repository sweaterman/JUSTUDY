package com.justudy.backend.member.repository;

import com.justudy.backend.login.dto.response.LoginResponse;
import com.justudy.backend.member.domain.MemberEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.justudy.backend.file.domain.QUploadFileEntity.uploadFileEntity;
import static com.justudy.backend.member.domain.QMemberCategoryEntity.memberCategoryEntity;
import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberEntity> findBySequenceWithJoin(Long sequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(memberEntity)
                .join(memberEntity.categories, memberCategoryEntity).fetchJoin()
                .join(memberEntity.imageFile, uploadFileEntity).fetchJoin()
                .where(memberEntity.sequence.eq(sequence))
                .fetchOne());
    }

    @Override
    public Optional<LoginResponse> findLoginInfoByUserId(String userId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.fields(LoginResponse.class,
                        memberEntity.sequence.as("loginSequence"),
                        memberEntity.password,
                        memberEntity.nickname))
                .from(memberEntity)
                .where(memberEntity.userId.eq(userId),
                        memberEntity.isBanned.eq(false),
                        memberEntity.isDeleted.eq(false))
                .fetchOne());
    }

    @Override
    public Optional<String> findUserId(String userId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.userId)
                .from(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .fetchOne()
        );
    }

    @Override
    public Optional<String> findNickname(String nickname) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.nickname)
                .from(memberEntity)
                .where(memberEntity.nickname.eq(nickname))
                .fetchOne()
        );
    }

    @Override
    public Optional<String> findSsafyId(String ssafyId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.ssafyId)
                .from(memberEntity)
                .where(memberEntity.ssafyId.eq(ssafyId))
                .fetchOne()
        );
    }

    @Override
    public Optional<String> findMmId(String mmId) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.mmId)
                .from(memberEntity)
                .where(memberEntity.isDeleted.eq(false),
                        memberEntity.isBanned.eq(false),
                        memberEntity.mmId.eq(mmId))
                .fetchOne());
    }

    @Override
    public Optional<Long> findSequenceByNickname(String nickname) {
        return Optional.ofNullable(queryFactory
                .select(memberEntity.sequence)
                .from(memberEntity)
                .where(memberEntity.nickname.eq(nickname))
                .fetchFirst()
        );
    }
}
