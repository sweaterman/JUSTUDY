package com.justudy.backend.member.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.exception.ConflictRequest;
import com.justudy.backend.member.exception.InvalidRequest;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.justudy.backend.member.dto.request.MemberCreate.MemberCreateBuilder;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MemberServiceTest {

    private MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private MemberService memberService;

    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";

    @BeforeEach
    public void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    @DisplayName("회원정보 수정페이지 반환")
    void getModifyPage() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findById(1L))
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

        BDDMockito.given(memberRepository.findAll())
                .willReturn(List.of(savedMember));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .userId(USER_ID)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request))
                .isInstanceOf(ConflictRequest.class)
                .hasMessage("중복된 값이 존재합니다.");

    }

    @Test
    @DisplayName("닉네임 중복")
    void duplicatedNickname() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findAll())
                .willReturn(List.of(savedMember));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .nickname(NICKNAME)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request))
                .isInstanceOf(ConflictRequest.class)
                .hasMessage("중복된 값이 존재합니다.");
    }

    @Test
    @DisplayName("ssfay학번 중복")
    void duplicatedSsafyId() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findAll())
                .willReturn(List.of(savedMember));

        //when
        MemberCreate request = makeMemberCreateBuilder()
                .ssafyId(SSAFY_ID)
                .build();

        //expected
        assertThatThrownBy(()-> memberService.saveMember(request))
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
        assertThatThrownBy(() -> memberService.saveMember(request))
                .isInstanceOf(InvalidRequest.class)
                .hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("멤버 업데이트")
    void editMember() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findById(1L))
                .willReturn(Optional.of(savedMember));

        MemberEdit editRequest = MemberEdit.builder()
                .nickname(NICKNAME)
                .phone("9999999999")
                .email("shinkwang.dev@gmail.com")
                .region("DAEJEON")
                .dream("그만하자")
                .introduction("나는 싸피생이다.")
                .build();

        //when
        memberService.editMember(1L, editRequest);

        //then
        assertThat(savedMember.getNickname()).isEqualTo(editRequest.getNickname());
        assertThat(savedMember.getPhone()).isEqualTo(editRequest.getPhone());
        assertThat(savedMember.getEmail()).isEqualTo(editRequest.getEmail());
        assertThat(savedMember.getRegion()).isEqualTo(Region.valueOf(editRequest.getRegion()));
        assertThat(savedMember.getDream()).isEqualTo(editRequest.getDream());
        assertThat(savedMember.getIntroduction()).isEqualTo(editRequest.getIntroduction());
    }

    @Test
    @DisplayName("멤버 비밀번호도 함께 업데이트")
    void editMemberWithPassword() {
        //given
        MemberEntity savedMember = makeTestMember(USER_ID, NICKNAME, SSAFY_ID);

        BDDMockito.given(memberRepository.findById(1L))
                .willReturn(Optional.of(savedMember));

        MemberEdit editRequest = MemberEdit.builder()
                .nickname(NICKNAME)
                .password("0123456789")
                .passwordCheck("0123456789")
                .phone("9999999999")
                .email("shinkwang.dev@gmail.com")
                .region("DAEJEON")
                .dream("그만하자")
                .introduction("나는 싸피생이다.")
                .build();

        //when
        memberService.editMember(1L, editRequest);

        //then
        assertThat(savedMember.getNickname()).isEqualTo(editRequest.getNickname());
        assertThat(savedMember.getPassword()).isEqualTo(editRequest.getPassword());
        assertThat(savedMember.getPhone()).isEqualTo(editRequest.getPhone());
        assertThat(savedMember.getEmail()).isEqualTo(editRequest.getEmail());
        assertThat(savedMember.getRegion()).isEqualTo(Region.valueOf(editRequest.getRegion()));
        assertThat(savedMember.getDream()).isEqualTo(editRequest.getDream());
        assertThat(savedMember.getIntroduction()).isEqualTo(editRequest.getIntroduction());
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
                .category(new String[]{"JAVA", "Spring", "JPA"})
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
