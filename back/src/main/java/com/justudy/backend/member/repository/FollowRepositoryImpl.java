package com.justudy.backend.member.repository;

import com.justudy.backend.member.domain.MemberFollow;
import com.justudy.backend.member.dto.response.FollowResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.justudy.backend.file.domain.QUploadFileEntity.uploadFileEntity;
import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;
import static com.justudy.backend.member.domain.QMemberFollow.memberFollow;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberFollow> getFollowByFromAndTo(Long fromSequence, Long toSequence) {
        return Optional.ofNullable(queryFactory
                .selectFrom(memberFollow)
                .join(memberFollow.fromMember, memberEntity).fetchJoin()
                .join(memberFollow.toMember, memberEntity).fetchJoin()
                .where(memberFollow.fromMember.sequence.eq(fromSequence),
                        memberFollow.toMember.sequence.eq(toSequence))
                .fetchFirst());
    }

    @Override
    public List<FollowResponse> getMyFollow(Long fromSequence) {
        return queryFactory
                .select(Projections.fields(FollowResponse.class,
                        memberFollow.toMember.sequence.as("memberSequence"),
                        memberFollow.toMember.nickname,
                        memberFollow.toMember.imageFile.sequence.as("imageSequence")))
                .from(memberFollow)
                .join(memberFollow.fromMember, memberEntity)
                .join(memberFollow.toMember, memberEntity)
                .join(memberFollow.toMember.imageFile, uploadFileEntity)
                .where(memberFollow.fromMember.sequence.eq(fromSequence))
                .fetch();
    }

    @Override
    public List<FollowResponse> getMyFollower(Long toSequence) {
        return queryFactory
                .select(Projections.fields(FollowResponse.class,
                        memberFollow.fromMember.sequence.as("memberSequence"),
                        memberFollow.fromMember.nickname,
                        memberFollow.fromMember.imageFile.sequence.as("imageSequence")))
                .from(memberFollow)
                .join(memberFollow.fromMember, memberEntity)
                .join(memberFollow.fromMember.imageFile, uploadFileEntity)
                .join(memberFollow.toMember, memberEntity)
                .where(memberFollow.toMember.sequence.eq(toSequence))
                .fetch();

    }
}
