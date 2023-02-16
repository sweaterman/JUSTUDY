package com.justudy.backend.member.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.exception.CategoryNotFound;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.common.validate.Validation;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.exception.ConflictRequest;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.FileStore;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.member.domain.MemberCategoryEntity;
import com.justudy.backend.member.domain.MemberEditor;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRole;
import com.justudy.backend.member.dto.request.MatterMostRequest;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.MatterMostResponse;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.dto.response.ProfileResponse;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
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

    private final UploadFileService uploadFileService;

    private final CommunityService communityService;

    private final FileStore fileStore;

    private final BCryptPasswordEncoder passwordEncoder;

    private static String[] fromCategoryToArray(List<MemberCategoryEntity> categories) {
        List<String> categoryToString = categories.stream().map(category -> category.getCategory().getValue())
                .collect(Collectors.toList());
        String[] categoryResponse = categoryToString.toArray(new String[categoryToString.size()]);
        return categoryResponse;
    }

    @Transactional
    public Long saveMember(MemberCreate request, UploadFileEntity basicImage) {
        validateCreateRequest(request);

        String encodePassword = passwordEncoder.encode(request.getPassword());
        MemberEntity member = request.toEntity(encodePassword);
        member.changeImage(basicImage);
        addCategory(request, member);

        memberRepository.save(member);
        return member.getSequence();
    }

    @Transactional
    public Long saveAdmin(MemberCreate request, UploadFileEntity basicImage) {
        validateCreateRequest(request);

        String encodePassword = passwordEncoder.encode(request.getPassword());
        MemberEntity member = request.toEntity(encodePassword);
        member.changeImage(basicImage);
        member.changeRole(MemberRole.ADMIN);
        addCategory(request, member);

        memberRepository.save(member);
        return member.getSequence();
    }

    public MypageResponse getMypage(Long loginSequence) {
//        MemberEntity findMember = memberRepository.findBySequenceWithJoin(loginSequence)
//                .orElseThrow(() -> new MemberNotFound());
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createMypageResponse(findMember);
    }

    public List<CommunityListResponse> getMyBookmarks(Long loginSequence) {
        List<CommunityListResponse> myBookmarks = communityService.getMyBookmarks(loginSequence);
        log.info("[getMyBookmarks] myBookmarks = {}", myBookmarks);
        return myBookmarks;
    }

    public List<CommunityListResponse> getMyLoves(Long loginSequence) {
        return communityService.getMyLoves(loginSequence);
    }

    public ModifyPageResponse getModifyPage(Long loginSequence) {
        MemberEntity findMember = memberRepository.findBySequenceWithJoin(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createModifyPageResponse(findMember);
    }

    public ProfileResponse getProfile(Long memberSequence) {
        MemberEntity findMember = memberRepository.findBySequenceWithJoin(memberSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createProfileResponse(findMember);
    }

    @Transactional
    public Long deleteMember(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());
        findMember.deleteMember();
        return findMember.getSequence();
    }

    @Transactional
    public Long editMember(Long loginSequence, MemberEdit editRequest, MultipartFile multipartFile) throws IOException {
        MemberEntity findMember = memberRepository.findBySequenceWithJoin(loginSequence)
                .orElseThrow(() -> new MemberNotFound());
        validateEditRequest(findMember, editRequest);

        UploadFileEntity uploadImage = fileStore.storeFile(multipartFile);
        saveUploadImage(uploadImage);

        MemberEditor.MemberEditorBuilder editorBuilder = findMember.toEditor();

        MemberEditor memberEditor = editorBuilder
                .nickname(editRequest.getNickname())
                .password(encodePassword(editRequest.getPassword()))
                .phone(editRequest.getPhone())
                .email(editRequest.getEmail())
                .region(Region.valueOf(editRequest.getRegion()))
                .dream(editRequest.getDream())
                .introduction(editRequest.getIntroduction())
                .imageFile(uploadImage)
                .build();

        List<MemberCategoryEntity> newCategories = createNewMemberCategories(editRequest);
        findMember.changeMemberCategory(newCategories);

        findMember.edit(memberEditor);
        findMember.changeModifiedTime(LocalDateTime.now());

        return findMember.getSequence();
    }

    public MemberEntity getMember(Long loginSequence) {
        return memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
    }

    public Long getSequenceByNickname(String nickname) {
        return memberRepository.findSequenceByNickname(nickname)
                .orElseThrow(MemberNotFound::new);
    }

    private void saveUploadImage(UploadFileEntity uploadImage) {
        if (uploadImage != null) {
            uploadFileService.saveUploadFile(uploadImage);
        }
    }

    private boolean validateImageFile(Long oldImageSequence, Long newImageSequence) {
        if (isSameImage(oldImageSequence, newImageSequence)) {
            return false;
        }
        if (isBasicImage(oldImageSequence)) {
            return false;
        }
        deleteOldImageFile(oldImageSequence);
        return true;
    }

    private Long deleteOldImageFile(Long oldImageSequence) {
        return uploadFileService.deleteUploadFile(oldImageSequence);
    }

    private boolean isSameImage(Long oldImageSequence, Long newImageSequence) {
        if (oldImageSequence == newImageSequence) {
            return true;
        }
        return false;
    }

    private boolean isBasicImage(Long sequence) {
        if (ImageConst.BASIC_MEMBER_IMAGE == sequence) {
            return true;
        }
        return false;
    }

    private List<MemberCategoryEntity> createNewMemberCategories(MemberEdit editRequest) {
        List<MemberCategoryEntity> memberCategories = new ArrayList<>();

        for (String c : editRequest.getCategory()) {
            CategoryEntity category = categoryRepository.findByValue(c)
                    .orElseThrow(CategoryNotFound::new);
            MemberCategoryEntity memberCategory = MemberCategoryEntity.createMemberCategory(category);
            memberCategories.add(memberCategory);
        }

        return memberCategories;
    }

//    private void validateSessionUser(Long loginSequence, MemberRole role) {
//        MemberEntity findMember = memberRepository.findById(loginSequence)
//                .orElseThrow(() -> new MemberNotFound());
//
//        if (!findMember.getRole().equals(role)) {
//            throw new ForbiddenRequest();
//        }
//    }

    private void addCategory(MemberCreate request, MemberEntity member) {
        log.info("request.getCategory.length = ", request.getCategory().length);
        List<CategoryEntity> categories = Arrays.stream(request.getCategory())
                .map(category -> (categoryRepository.findByValue(category)
                        .orElseThrow(CategoryNotFound::new)))
                .collect(Collectors.toList());
        for (CategoryEntity category : categories) {
            MemberCategoryEntity memberCategory = MemberCategoryEntity.createMemberCategory(category);
            member.addMemberCategory(memberCategory);
        }
    }

    private ProfileResponse createProfileResponse(MemberEntity member) {
        return ProfileResponse.builder()
                .nickname(member.getNickname())
                .category(fromCategoryToArray(member.getCategories()))
                .dream(member.getDream())
                .introduction(member.getIntroduction())
                .level(member.getLevel().getValue())
                .imageSequence(member.getImageFile().getSequence()) //imageFile Sequence
                .badgeCount(member.getBadgeCount())
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
                .category(fromCategoryToArray(member.getCategories()))
                .dream(member.getDream())
                .introduction(member.getIntroduction())
                .imageSequence(member.getImageFile().getSequence()) //imageFile Sequence
                .build();
    }

    private MypageResponse createMypageResponse(MemberEntity member) {
        return MypageResponse.builder()
                .nickname(member.getNickname())
                .category(fromCategoryToArray(member.getCategories()))
                .dream(member.getDream())
                .status(member.getStatus().getValue())
                .badgeCount(member.getBadgeCount())
                .level(member.getLevel().getValue())
                .imageSequence(member.getImageFile().getSequence()) //imageFile Sequence
                .build();
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
                || StringUtils.hasText(newPasswordCheck)) {
            isNotEqualPassword(newPassword, newPasswordCheck);
        }
    }

    private String encodePassword(String password) {
        if (password == null) {
            return null;
        }
        return passwordEncoder.encode(password);
    }

    public ResponseEntity<?> validateMatterMost(String mmId, String mmPassword) {
        isDuplicatedMmId(mmId);

        URI uri = UriComponentsBuilder
                .fromUriString("https://meeting.ssafy.com")
                .path("/api/v4/users/login")
                .encode()
                .build()
                .toUri();

        MatterMostRequest matterMostRequest = new MatterMostRequest(mmId, mmPassword);
        RequestEntity<MatterMostRequest> requestEntity = RequestEntity
                .post(uri)
                .header("host", "meeting.ssafy.com")
                .header("origin", "https://meeting.ssafy.com")
                .body(matterMostRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MatterMostResponse> response = restTemplate.exchange(requestEntity, MatterMostResponse.class);
        return ResponseEntity.status(200).body(response.getBody());
    }

    public void validEditNickname(Long loginSequence, String nickname) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
        if (findMember.getNickname().equals(nickname)) {
            return;
        }
        isDuplicatedNickname(nickname);
    }

    public void isDuplicatedMmId(String mmId) {
        if (memberRepository.findMmId(mmId).isPresent()) {
            throw new ConflictRequest("mmId", "이미 가입된 MatterMostId 입니다.");
        }
    }

    public void isDuplicatedUserId(String userId) {
        if (memberRepository.findUserId(userId).isPresent()) {
            throw new ConflictRequest("userId", "이미 가입된 아이디입니다.");
        }
    }

    public void isDuplicatedNickname(String nickname) {
        if (memberRepository.findNickname(nickname).isPresent()) {
            throw new ConflictRequest("nickname", "이미 가입된 닉네임입니다.");
        }
    }

    public void isDuplicatedSsafyId(String ssafyId) {
        if (memberRepository.findSsafyId(ssafyId).isPresent()) {
            throw new ConflictRequest("ssafyId", "이미 가입된 SSAFY학번입니다.");
        }
    }

    private void isNotEqualPassword(String password, String passwordCheck) {
        if (password == null || !password.equals(passwordCheck)) {
            throw new InvalidRequest("password", "비밀번호와 비밀번호확인이 다릅니다.");
        }
    }
}
