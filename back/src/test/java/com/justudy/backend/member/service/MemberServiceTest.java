package com.justudy.backend.member.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRegion;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
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
        boolean result = memberService.isDuplicatedUserId(request.getUserId());

        //then
        assertThat(result).isTrue();
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
        boolean result = memberService.isDuplicatedNickname(request.getNickname());

        //then
        Assertions.assertThat(result).isTrue();
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
        boolean result = memberService.isDuplicatedSsafyId(request.getSsafyId());

        //then
        Assertions.assertThat(result).isTrue();
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
                .region(MemberRegion.SEOUL)
                .dream("백엔드 취업희망")
                .introduction("안녕하세요")
                .build();
    }
}
