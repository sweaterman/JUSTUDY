package com.justudy.backend.admin.dto.response;

import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.Data;

@Data
public class AdminMemberDetail {

    private Long imageSequence;

    private String userId;

    private String username;

    private String nickname;

    private String ssafyId;

    private String mmId;

    private String phone;

    private String email;

    private String dream;

    private String introduction;

    private String createdTime;

    private String modifiedTime;

    private boolean isBanned;

    private boolean isDeleted;

    public AdminMemberDetail(MemberEntity member) {
        this.imageSequence = member.getImageFile().getSequence();
        this.userId = member.getUserId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.ssafyId = member.getSsafyId();
        this.mmId = member.getMmId();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.dream = member.getDream();
        this.introduction = member.getIntroduction();
        this.createdTime = DateChanger.format(member.getCreatedTime());
        this.modifiedTime = DateChanger.format(member.getModifiedTime());
        this.isBanned = member.isBanned();
        this.isDeleted = member.isDeleted();
    }
}
