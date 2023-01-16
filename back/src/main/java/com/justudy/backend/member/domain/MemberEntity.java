package com.justudy.backend.member.domain;


import com.justudy.backend.file.domain.FileEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_seq")
    private Long sequence;

    @Column(name = "member_user_id")
    private String userId;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_ssafy_id")
    private String ssafyId;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_region")
    @Enumerated(EnumType.STRING)
    private MemberRegion region;

    @Column(name = "member_dream")
    private String dream;

    @Column(name = "member_introduction")
    private String introduction;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    private FileEntity image;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "member_status")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "member_level")
    @Enumerated(EnumType.STRING)
    private MemberLevel level;

    @Column(name = "member_mm_id")
    private String mmId;

    @Column(name = "member_mm_valid")
    private boolean isMMValid;

    @Column(name = "member_badge_count")
    private Integer badgeCount;

    @Column(name = "member_is_deleted")
    private boolean isDeleted;

    @Column(name = "member_is_banned")
    private boolean isBanned;
}
