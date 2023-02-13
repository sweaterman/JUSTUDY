package com.justudy.backend.member.domain;


import com.justudy.backend.common.enum_util.Level;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.exception.ForbiddenRequest;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long sequence;

    @Column(name = "member_user_id")
    private String userId;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_username")
    private String username;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_ssafy_id")
    private String ssafyId;

    @Column(name = "member_mm_id")
    private String mmId;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_region")
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(name = "member_dream")
    private String dream;

    @Column(name = "member_introduction")
    private String introduction;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "upload_file_seq")
    private UploadFileEntity imageFile;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "member_status")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "member_level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "member",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    List<MemberCategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<CommunityEntity> boards = new ArrayList<>();

    @Column(name = "member_badge_count")
    private Integer badgeCount;

    @Column(name = "member_is_deleted")
    private boolean isDeleted;

    @Column(name = "member_is_banned")
    private boolean isBanned;

    @Column(name = "member_created_time")
    private LocalDateTime createdTime;

    @Column(name = "member_modified_time")
    private LocalDateTime modifiedTime;

    @Builder
    public MemberEntity(String userId, String password,
                        String username, String nickname,
                        String ssafyId, String mmId, String phone, String email,
                        Region region, String dream, String introduction,
                        UploadFileEntity imageFile) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.ssafyId = ssafyId;
        this.mmId = mmId;
        this.phone = phone;
        this.email = email;
        this.region = region;
        this.dream = dream;
        this.introduction = introduction;

        this.imageFile = imageFile;

        this.role = MemberRole.USER;
        this.status = MemberStatus.ONLINE;
        this.level = Level.BEGINNER;

        this.badgeCount = 0;
        this.isDeleted = false;
        this.isBanned = false;

        this.createdTime = LocalDateTime.now();
        this.modifiedTime = createdTime;
    }

    public MemberEditor.MemberEditorBuilder toEditor() {
        return MemberEditor.builder()
                .nickname(nickname)
                .password(password)
                .phone(phone)
                .email(email)
                .region(region)
                .category(categories)
                .dream(dream)
                .introduction(introduction)
                .imageFile(imageFile);
    }

    public void edit(MemberEditor memberEditor) {
        nickname = memberEditor.getNickname();
        password = memberEditor.getPassword();
        phone = memberEditor.getPhone();
        email = memberEditor.getEmail();
        region = memberEditor.getRegion();
        categories = memberEditor.getCategory();
        dream = memberEditor.getDream();
        introduction = memberEditor.getIntroduction();
        imageFile = memberEditor.getImageFile();
        modifiedTime = LocalDateTime.now();
    }

    public void banMember() {
        if (role.equals(MemberRole.ADMIN)) {
            throw new ForbiddenRequest("role", "ADMIN 유저는 밴할 수 없습니다.");
        }
        isBanned = true;
    }

    public void deleteMember() {
        isDeleted = true;
    }

    public void changeRole(MemberRole role) {
        this.role = role;
    }

    public void changeImage(UploadFileEntity imageFile) {
        this.imageFile = imageFile;
    }

    public void changeModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    //== 연관관계 편의메소드 ==//
    public void addMemberCategory(MemberCategoryEntity memberCategory) {
        this.categories.add(memberCategory);
        memberCategory.addMember(this);
    }

    public void addBoard(CommunityEntity board) {
        this.boards.add(board);
    }


    public void changeMemberCategory(List<MemberCategoryEntity> categories) {
        for (MemberCategoryEntity category : categories) {
            addMemberCategory(category);
        }
    }
}
