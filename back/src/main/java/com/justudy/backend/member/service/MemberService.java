package com.justudy.backend.member.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.member.domain.MemberCategoryEntity;
import com.justudy.backend.member.domain.MemberEditor;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRole;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.dto.response.ProfileResponse;
import com.justudy.backend.member.exception.ConflictRequest;
import com.justudy.backend.member.exception.ForbiddenRequest;
import com.justudy.backend.member.exception.InvalidRequest;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveMember(MemberCreate request) {
        validateCreateRequest(request);

        MemberEntity member = request.toEntity();
        addCategory(request, member);

        memberRepository.save(member);
        return member.getSequence();
    }

    public MypageResponse getMypage(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createMypageResponse(findMember);
    }

    public ModifyPageResponse getModifyPage(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createModifyPageResponse(findMember);
    }

    public ProfileResponse getProfile(Long memberSequence) {
        MemberEntity findMember = memberRepository.findById(memberSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createProfileResponse(findMember);
    }

    @Transactional
    public Long banMember(Long loginSequence, Long memberSequence) {
        validateSessionUser(loginSequence, MemberRole.ADMIN);

        MemberEntity targetMember = memberRepository.findById(memberSequence)
                .orElseThrow(() -> new MemberNotFound());
        targetMember.banMember();
        return targetMember.getSequence();
    }

    @Transactional
    public Long deleteMember(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());
        findMember.deleteMember();
        return findMember.getSequence();
    }

    @Transactional
    public Long editMember(Long loginSequence, MemberEdit editRequest) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());
        validateEditRequest(findMember, editRequest);

        MemberEditor.MemberEditorBuilder editorBuilder = findMember.toEditor();

        MemberEditor memberEditor = editorBuilder
                .nickname(editRequest.getNickname())
                .password(editRequest.getPassword())
                .phone(editRequest.getPhone())
                .email(editRequest.getEmail())
                .region(Region.valueOf(editRequest.getRegion()))
                .dream(editRequest.getDream())
                .introduction(editRequest.getIntroduction())
                .build();

        List<MemberCategoryEntity> newCategories = createNewMemberCategories(editRequest);

        findMember.changeMemberCategory(newCategories);
        findMember.edit(memberEditor);

        return findMember.getSequence();
    }

    private List<MemberCategoryEntity> createNewMemberCategories(MemberEdit editRequest) {
        List<MemberCategoryEntity> memberCategories = new ArrayList<>();

        for (String c : editRequest.getCategory()) {
            CategoryEntity category = categoryRepository.findByName(c)
                    .orElseThrow(() -> new InvalidRequest("category", "잘못된 카테고리 이름입니다."));
            MemberCategoryEntity memberCategory = MemberCategoryEntity.createMemberCategory(category);
            memberCategories.add(memberCategory);
        }

        return memberCategories;
    }

    private void addCategory(MemberCreate request, MemberEntity member) {
        List<CategoryEntity> categories = Arrays.stream(request.getCategory())
                .map(category -> (categoryRepository.findByName(category)
                        .orElseThrow(InvalidRequest::new)))
                .collect(Collectors.toList());
        for (CategoryEntity category : categories) {
            MemberCategoryEntity memberCategory = MemberCategoryEntity.createMemberCategory(category);
            member.addMemberCategory(memberCategory);
        }
    }

    private void validateSessionUser(Long loginSequence, MemberRole role) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        if (!findMember.getRole().equals(role)) {
            throw new ForbiddenRequest();
        }
    }

    private ProfileResponse createProfileResponse(MemberEntity member) {
        return ProfileResponse.builder()
                .nickname(member.getNickname())
                .category(getCategoryArray(member.getCategories()))
                .dream(member.getDream())
                .introduction(member.getIntroduction())
                .level(member.getLevel().getValue())
                .build();
    }

    private ModifyPageResponse createModifyPageResponse(MemberEntity member) {
        return ModifyPageResponse.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .region(member.getRegion().getValue())
                .level(member.getLevel().getValue())
                .ssafyId(member.getSsafyId())
                .userId(member.getUserId())
                .phone(member.getPhone())
                .email(member.getEmail())
                .category(getCategoryArray(member.getCategories()))
                .dream(member.getDream())
                .introduction(member.getIntroduction())
                .build();
    }

    private MypageResponse createMypageResponse(MemberEntity member) {
        return MypageResponse.builder()
                .nickname(member.getNickname())
                .category(getCategoryArray(member.getCategories()))
                .dream(member.getDream())
                .status(member.getStatus().getValue())
                .badgeCount(member.getBadgeCount())
                .level(member.getLevel().getValue())
                .build();
    }

    private static String[] getCategoryArray(List<MemberCategoryEntity> categories) {
        List<String> categoryToString = categories.stream().map(category -> category.getCategory().getName())
                .collect(Collectors.toList());
        String[] categoryResponse = categoryToString.toArray(new String[categoryToString.size()]);
        return categoryResponse;
    }

    private void validateCreateRequest(MemberCreate request) {
        isDuplicatedUserId(request.getUserId());
        isDuplicatedSsafyId(request.getSsafyId());
        isDuplicatedNickname(request.getNickname());
        isNotEqualPassword(request.getPassword(), request.getPasswordCheck());
    }

    private void validateEditRequest(MemberEntity findMember, MemberEdit editRequest) {
        String oldNickname = findMember.getNickname();
        String newNickname = editRequest.getNickname();
        if (!oldNickname.equals(newNickname)) {
            isDuplicatedNickname(newNickname);
        }

        String newPassword = editRequest.getPassword();
        String newPasswordCheck = editRequest.getPasswordCheck();
        if (StringUtils.hasText(newPassword)
                && StringUtils.hasText(newPasswordCheck)) {
            isNotEqualPassword(newPassword, newPasswordCheck);
        }
    }

    private void isDuplicatedUserId(String userId) {
        List<MemberEntity> members = memberRepository.findAll();
        if (members.stream().anyMatch(member -> member.getUserId().equals(userId))) {
            throw new ConflictRequest("userId", "이미 가입된 아이디입니다.");
        }
    }

    private void isDuplicatedNickname(String nickname) {
        List<MemberEntity> members = memberRepository.findAll();
        if (members.stream().anyMatch(member -> member.getNickname().equals(nickname))) {
            throw new ConflictRequest("nickname", "이미 가입된 닉네임입니다.");
        }
    }

    private void isDuplicatedSsafyId(String ssafyId) {
        List<MemberEntity> members = memberRepository.findAll();
        if (members.stream().anyMatch(member -> member.getSsafyId().equals(ssafyId))) {
            throw new ConflictRequest("ssafyId", "이미 가입된 SSAFY학번입니다.");
        }
    }

    private void isNotEqualPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new InvalidRequest("password", "비밀번호와 비밀번호확인이 다릅니다.");
        }
    }
}
