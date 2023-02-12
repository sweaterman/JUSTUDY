package com.justudy.backend.admin.repository;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.request.MemberSearchType;
import com.justudy.backend.member.domain.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.justudy.backend.member.domain.QMemberEntity.*;

@RequiredArgsConstructor
@Repository
public class AdminRepository {

    private final JPAQueryFactory queryFactory;

    public Long getCountOfMembers() {
        return queryFactory.select(memberEntity.count())
                .from(memberEntity)
                .fetchOne();
    }

    public List<MemberEntity> getMembers(MemberSearch memberSearch) {
        return queryFactory.selectFrom(memberEntity)
                .where(eqTypeAndSearch(memberSearch))
                .limit(memberSearch.getSize())
                .offset(memberSearch.getOffset())
                .orderBy(memberEntity.sequence.desc())
                .fetch();
    }
    public Long getCountMemberBySearch(MemberSearch memberSearch) {
        return queryFactory.select(memberEntity.count())
                .where(eqTypeAndSearch(memberSearch))
                .fetchOne();
    }

    private BooleanExpression eqTypeAndSearch(MemberSearch memberSearch) {
        MemberSearchType type = memberSearch.getType();
        if (type == null) {
            return null;
        }
        if (memberSearch.getSearch() == null) {
            return null;
        }
        return type.getExpression(memberSearch.getSearch());
    }
}
