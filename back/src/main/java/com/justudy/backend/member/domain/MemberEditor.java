package com.justudy.backend.member.domain;

import com.justudy.backend.common.enum_util.Region;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberEditor {

    private String nickname;

    private String password;

    private String phone;

    private String email;

    private Region region;

    private List<MemberCategoryEntity> category;

    private String dream;

    private String introduction;

    public MemberEditor(String nickname, String password,
                        String phone, String email,
                        Region region, List<MemberCategoryEntity> category,
                        String dream, String introduction) {
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.region = region;
        this.category = category;
        this.dream = dream;
        this.introduction = introduction;
    }

    public static MemberEditorBuilder builder() {
        return new MemberEditorBuilder();
    }

    public static class MemberEditorBuilder {
        private String nickname;
        private String password;
        private String phone;
        private String email;
        private Region region;
        private List<MemberCategoryEntity> category;
        private String dream;
        private String introduction;

        MemberEditorBuilder() { }

        public MemberEditorBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }
        public MemberEditorBuilder password(final String password) {
            if (password != null) {
                this.password = password;
            }
            return this;
        }
        public MemberEditorBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }
        public MemberEditorBuilder email(final String email) {
            this.email = email;
            return this;
        }
        public MemberEditorBuilder region(final Region region) {
            this.region = region;
            return this;
        }
        public MemberEditorBuilder category(final List<MemberCategoryEntity> category) {
            this.category = category;
            return this;
        }
        public MemberEditorBuilder dream(final String dream) {
            this.dream = dream;
            return this;
        }
        public MemberEditorBuilder introduction(final String introduction) {
            this.introduction = introduction;
            return this;
        }

        public MemberEditor build() {
            return new MemberEditor(this.nickname, this.password,
                    this.phone, this.email, this.region,
                    this.category, this.dream, this.introduction);
        }
    }
}
