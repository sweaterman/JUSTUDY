package com.justudy.backend.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.common.enum_util.Level;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberStatus;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .build();
    }


    @Test
    @DisplayName("POST /register 요청")
    void signupMember() throws Exception {
        //given
        MemberCreate request = MemberCreate.builder()
                .userId("sklee0206")
                .password("1234")
                .passwordCheck("1234")
                .username("이싸피")
                .nickname("돌로스원숭이")
                .ssafyId("0847968")
                .phone("01051391111")
                .email("ssafylee@ssafy.com")
                .mmId("sklee0206")
                .region("SEOUL")
                .dream("백엔드취업 희망")
                .category(new String[]{"JAVA", "Spring"})
                .introduction("이신광이다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("GET /mypage/member")
    void getMemberOfMypage() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, 1L);

        BDDMockito.given(memberService.getMypage(1L))
                .willReturn(makeTestMypageResponse());

        //expected
        mockMvc.perform(get("/api/mypage/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("닉네임"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dream").value("백엔드개발자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(MemberStatus.ONLINE.getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.badgeCount").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value(Level.BEGINNER.getValue()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("GET /mypage/modify")
    void getModifyPage() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, 1L);

        BDDMockito.given(memberService.getModifyPage(1L))
                .willReturn(makeTestModifyPageResponse());

        //expected
        mockMvc.perform(get("/api/mypage/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static ModifyPageResponse makeTestModifyPageResponse() {
        return ModifyPageResponse.builder()
                .username("서주광")
                .nickname("돌로스원숭숭")
                .region("서울")
                .level("초보")
                .ssafyId("0845111")
                .userId("tjwnrhkd")
                .phone("0101111111")
                .email("tjwnrhkd@naver.com")
                .category(new String[]{"Spring", "Java"})
                .dream("백엔드 + 프론트 마스터")
                .introduction("귀여운 돌로스 원숭숭입니다.")
                .build();
    }

    private static MypageResponse makeTestMypageResponse() {
        return MypageResponse.builder()
                .nickname("닉네임")
                .category(new String[] {"Java", "Spring"})
                .dream("백엔드개발자")
                .status(MemberStatus.ONLINE.getValue())
                .badgeCount(2)
                .level(Level.BEGINNER.getValue())
                .build();
    }
}
