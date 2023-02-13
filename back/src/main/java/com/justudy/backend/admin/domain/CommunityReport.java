package com.justudy.backend.admin.domain;

import com.justudy.backend.community.domain.CommunityEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommunityReport extends Report{

//    private CommunityEntity targetCommunity;


}
