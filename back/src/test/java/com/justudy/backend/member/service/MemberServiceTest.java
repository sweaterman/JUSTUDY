package com.justudy.backend.member.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.service.FileStore;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRole;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.exception.ConflictRequest;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;

import static com.justudy.backend.member.dto.request.MemberCreate.MemberCreateBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class MemberServiceTest {

    private MemberRepository memberRepository = Mockito.mock(MemberRepository.class);

    private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);

    private CommunityService communityService = Mockito.mock(CommunityService.class);

    private UploadFileService uploadFileService = Mockito.mock(UploadFileService.class);

    private BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    private FileStore fileStore = Mockito.mock(FileStore.class);

    private MemberService memberService;

    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";

    @BeforeEach
    public void setUp() {
        memberService = new MemberService(memberRepository, categoryRepository,
                uploadFileService, communityService,
                fileStore, passwordEncoder);
    }

    @Test
    @DisplayName("GET 회원정보 수정페이지 반환 API")
    void getModifyPage() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);
        UploadFileEntity imageFile = new UploadFileEntity("test", "testUuid");
        savedMember.changeImage(imageFile);

        BDDMockito.given(memberRepository.findBySequenceWithJoin(1L))
                .willReturn(Optional.of(savedMember));

        //when
        ModifyPageResponse modifyPage = memberService.getModifyPage(1L);

        //then
        assertThat(modifyPage.getUsername()).isEqualTo(savedMember.getUsername());
        assertThat(modifyPage.getCategory()).isEmpty();
    }

    @Test
    @DisplayName("유저 아이디 중복검증")
    void duplicatedUserId() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findUserId(USER_ID))
                .willReturn(Optional.of(USER_ID));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .userId(USER_ID)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request, null))
                .isInstanceOf(ConflictRequest.class)
                .hasMessage("중복된 값이 존재합니다.");

    }

    @Test
    @DisplayName("닉네임 중복")
    void duplicatedNickname() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findNickname(NICKNAME))
                .willReturn(Optional.of(NICKNAME));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .nickname(NICKNAME)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request, null))
                .isInstanceOf(ConflictRequest.class)
                .hasMessage("중복된 값이 존재합니다.");
    }

    @Test
    @DisplayName("ssfay학번 중복")
    void duplicatedSsafyId() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findSsafyId(SSAFY_ID))
                .willReturn(Optional.of(SSAFY_ID));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .ssafyId(SSAFY_ID)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request, null))
                .isInstanceOf(ConflictRequest.class)
                .hasMessage("중복된 값이 존재합니다.");
    }

    @Test
    @DisplayName("비밀번호 검증")
    void validPassword() {
        //given
        MemberCreate request = MemberCreate.builder()
                .password("1234")
                .passwordCheck("123")
                .build();

        //expected
        assertThatThrownBy(() -> memberService.saveMember(request, null))
                .isInstanceOf(InvalidRequest.class)
                .hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("멤버 업데이트")
    void editMember() throws IOException {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);
        UploadFileEntity oldImageFile = new UploadFileEntity("test", "testUuid");
        savedMember.changeImage(oldImageFile);

        CategoryEntity backend = new CategoryEntity("backend", "백엔드", 0L, null);
        CategoryEntity java = new CategoryEntity("java", "Java", 1L, null);
        java.addParentCategory(backend);
        CategoryEntity spring = new CategoryEntity("spring", "Spring", 1L, null);
        spring.addParentCategory(backend);
        CategoryEntity python = new CategoryEntity("python", "Python", 1L, null);
        python.addParentCategory(backend);

        BDDMockito.given(memberRepository.findBySequenceWithJoin(1L))
                .willReturn(Optional.of(savedMember));

        BDDMockito.given(categoryRepository.findByValue("Java"))
                .willReturn(Optional.of(java));

        BDDMockito.given(categoryRepository.findByValue("Spring"))
                .willReturn(Optional.of(spring));

        BDDMockito.given(categoryRepository.findByValue("Python"))
                .willReturn(Optional.of(python));

        MemberEdit editRequest = MemberEdit.builder()
                .nickname(NICKNAME)
                .phone("9999999999")
                .email("shinkwang.dev@gmail.com")
                .region("DAEJEON")
                .dream("그만하자")
                .category(new String[]{"Java", "Spring", "Python"})
                .introduction("나는 싸피생이다.")
                .build();

        UploadFileEntity newImageFile = new UploadFileEntity("newTest", "newTestUuid");

        //when
        memberService.editMember(1L, editRequest, null);

        //then
        assertThat(savedMember.getNickname()).isEqualTo(editRequest.getNickname());
        assertThat(savedMember.getPhone()).isEqualTo(editRequest.getPhone());
        assertThat(savedMember.getEmail()).isEqualTo(editRequest.getEmail());
        assertThat(savedMember.getRegion()).isEqualTo(Region.valueOf(editRequest.getRegion()));
        assertThat(savedMember.getDream()).isEqualTo(editRequest.getDream());
        assertThat(savedMember.getIntroduction()).isEqualTo(editRequest.getIntroduction());
        assertThat(savedMember.getCategories().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("멤버 비밀번호도 함께 업데이트")
    void editMemberWithPassword() throws IOException {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);
        UploadFileEntity oldImageFile = new UploadFileEntity("test", "testUuid");
        savedMember.changeImage(oldImageFile);

        CategoryEntity backend = new CategoryEntity("backend", "백엔드", 0L, null);
        CategoryEntity java = new CategoryEntity("java", "Java", 1L, null);
        java.addParentCategory(backend);
        CategoryEntity spring = new CategoryEntity("spring", "Spring", 1L, null);
        spring.addParentCategory(backend);
        CategoryEntity python = new CategoryEntity("python", "Python", 1L, null);
        python.addParentCategory(backend);

        BDDMockito.given(memberRepository.findBySequenceWithJoin(1L))
                .willReturn(Optional.of(savedMember));

        BDDMockito.given(categoryRepository.findByValue("Java"))
                .willReturn(Optional.of(java));

        BDDMockito.given(categoryRepository.findByValue("Spring"))
                .willReturn(Optional.of(spring));

        BDDMockito.given(categoryRepository.findByValue("Python"))
                .willReturn(Optional.of(python));

        BDDMockito.given(passwordEncoder.encode("0123456789"))
                .willReturn("abcdefghij");

        MemberEdit editRequest = MemberEdit.builder()
                .nickname(NICKNAME)
                .password("0123456789")
                .passwordCheck("0123456789")
                .phone("9999999999")
                .email("shinkwang.dev@gmail.com")
                .region("DAEJEON")
                .dream("그만하자")
                .category(new String[]{"Java", "Spring", "Python"})
                .introduction("나는 싸피생이다.")
                .build();

        //when
        memberService.editMember(1L, editRequest, null);

        //then
        assertThat(savedMember.getNickname()).isEqualTo(editRequest.getNickname());
        assertThat(savedMember.getPassword()).isEqualTo("abcdefghij");
        assertThat(savedMember.getPhone()).isEqualTo(editRequest.getPhone());
        assertThat(savedMember.getEmail()).isEqualTo(editRequest.getEmail());
        assertThat(savedMember.getRegion()).isEqualTo(Region.valueOf(editRequest.getRegion()));
        assertThat(savedMember.getDream()).isEqualTo(editRequest.getDream());
        assertThat(savedMember.getIntroduction()).isEqualTo(editRequest.getIntroduction());
        assertThat(savedMember.getCategories().size()).isEqualTo(3);
        assertThat(savedMember.getImageFile()).isEqualTo(oldImageFile);
    }

    @ParameterizedTest
    @CsvSource(value = {"'0123456789', null", "null, '0123456789'"}, nullValues = "null")
    @DisplayName("비밀번호, 비밀번호 확인이 하나씩 들어올 때")
    void passwordWithoutPasswordCheck(String password, String passwordCheck) throws IOException {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);
        UploadFileEntity oldImageFile = new UploadFileEntity("test", "testUuid");
        savedMember.changeImage(oldImageFile);

        CategoryEntity backend = new CategoryEntity("backend", "백엔드", 0L, null);
        CategoryEntity java = new CategoryEntity("java", "Java", 1L, null);
        java.addParentCategory(backend);
        CategoryEntity spring = new CategoryEntity("spring", "Spring", 1L, null);
        spring.addParentCategory(backend);
        CategoryEntity python = new CategoryEntity("python", "Python", 1L, null);
        python.addParentCategory(backend);

        BDDMockito.given(memberRepository.findBySequenceWithJoin(1L))
                .willReturn(Optional.of(savedMember));

        BDDMockito.given(categoryRepository.findByValue("Java"))
                .willReturn(Optional.of(java));
        BDDMockito.given(categoryRepository.findByValue("Spring"))
                .willReturn(Optional.of(spring));
        BDDMockito.given(categoryRepository.findByValue("Python"))
                .willReturn(Optional.of(python));

        MemberEdit editRequest = MemberEdit.builder()
                .nickname(NICKNAME)
                .password(password)
                .passwordCheck(passwordCheck)
                .phone("9999999999")
                .email("shinkwang.dev@gmail.com")
                .region("DAEJEON")
                .dream("그만하자")
                .category(new String[]{"Java", "Spring", "Python"})
                .introduction("나는 싸피생이다.")
                .build();

        //expected
        assertThatThrownBy(() -> memberService.editMember(1L, editRequest, null))
                .isInstanceOf(InvalidRequest.class)
                .hasMessage("잘못된 요청입니다.");
    }


    @Test
    @DisplayName("회원 탈퇴")
    void deleteMember() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findById(1L))
                .willReturn(Optional.of(savedMember));

        //when
        memberService.deleteMember(1L);

        //then
        assertThat(memberRepository.findById(1L).get().isDeleted()).isTrue();
    }





    private MemberCreateBuilder makeMemberCreateBuilder() {
        return MemberCreate.builder()
                .password("1234")
                .passwordCheck("1234")
                .username("이싸피")
                .phone("01051391111")
                .email("ssafylee@ssafy.com")
                .region("SEOUL")
                .dream("백엔드취업 희망")
                .category(new String[]{"JAVA", "Spring"})
                .introduction("이신광이다.");
    }


    private MemberEntity makeTestMember(String userId, String nickname, String ssafyId) {
        return MemberEntity.builder()
                .userId(userId)
                .password("1234")
                .username("이신광")
                .nickname(nickname)
                .ssafyId(ssafyId)
                .phone("01011111111")
                .email("ssafylee@ssafy.com")
                .region(Region.SEOUL)
                .dream("백엔드 취업희망")
                .introduction("안녕하세요")
                .build();
    }
}
