package com.justudy.backend.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.common.enum_util.Level;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberStatus;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.dto.response.ProfileResponse;
import com.justudy.backend.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(value = MemberController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private UploadFileService uploadFileService;

    @Test
    @DisplayName("POST /register 요청")
    void signupMember() throws Exception {
        //given
        UploadFileEntity imageFile = new UploadFileEntity("test", "test");
        ReflectionTestUtils.setField(imageFile, "sequence", 1L);
        BDDMockito.given(uploadFileService.getUploadFile(anyLong()))
                .willReturn(imageFile);

        MemberCreate request = MemberCreate.builder()
                .userId("sklee0206")
                .password("1234")
                .passwordCheck("1234")
                .username("이싸피")
                .nickname("돌로스원숭이")
                .ssafyId("0847968")
                .phone("01051391111")
                .email("ssafylee@ssafy.com")
                .region("SEOUL")
                .dream("백엔드취업 희망")
                .category(new String[]{"JAVA", "Spring"})
                .introduction("이신광이다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/api/member/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string(""))
                .andDo(print());

        BDDMockito.then(uploadFileService).should().getUploadFile(anyLong());
    }

    @Test
    @DisplayName("GET /check")
    void validateMember() throws Exception {
        //given
        final String NICKNAME = "nickname";
        final String SSAFY_ID = "078462";
        final String USER_ID = "sklee0206";

        BDDMockito.willThrow(InvalidRequest.class).given(memberService).isDuplicatedNickname(NICKNAME);

        mockMvc.perform(get("/api/member/check" + "?nickname=test&ssafyid=1234&userid=sklee0206"))
                .andDo(print());
    }

    @Test
    @DisplayName("GET /mypage")
    void getMemberOfMypage() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, 1L);

        MypageResponse mypageResponse = makeTestMypageResponse();

        BDDMockito.given(memberService.getMypage(1L))
                .willReturn(mypageResponse);

        //expected
        mockMvc.perform(get("/api/member/mypage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("닉네임"))
                .andExpect(jsonPath("$.dream").value("백엔드개발자"))
                .andExpect(jsonPath("$.status").value(MemberStatus.ONLINE.getValue()))
                .andExpect(jsonPath("$.badgeCount").value(2))
                .andExpect(jsonPath("$.level").value(Level.BEGINNER.getValue()))
                .andDo(print());
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
        mockMvc.perform(get("/api/member/mypage/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("GET /profiles/{memberSequence}")
    void getProfile() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, 1L);

        Long MEMBER_SEQUENCE = 2L;

        BDDMockito.given(memberService.getProfile(2L))
                .willReturn(makeTestProfile());

        //expected
        mockMvc.perform(get("/api/member/profiles/{memberSequence}", MEMBER_SEQUENCE)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value("테스트닉네임"))
                .andExpect(jsonPath("$.imageSequence").value(ImageConst.BASIC_MEMBER_IMAGE))
                .andExpect(jsonPath("$.dream").value("백엔드 희망"))
                .andExpect(jsonPath("$.introduction").value("테스트용"))
                .andExpect(jsonPath("$.level").value(Level.BEGINNER.getValue()))
                .andExpect(jsonPath("$.badgeCount").value(5))
                .andDo(print());
    }

    private ProfileResponse makeTestProfile() {
        return ProfileResponse.builder()
                .nickname("테스트닉네임")
                .category(new String[]{"Java", "Spring"})
                .imageSequence(ImageConst.BASIC_MEMBER_IMAGE)
                .dream("백엔드 희망")
                .introduction("테스트용")
                .level(Level.BEGINNER.getValue())
                .badgeCount(5)
                .build();
    }

    private ModifyPageResponse makeTestModifyPageResponse() {
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

    private MypageResponse makeTestMypageResponse() {
        return MypageResponse.builder()
                .nickname("닉네임")
                .category(new String[]{"Java", "Spring"})
                .dream("백엔드개발자")
                .status(MemberStatus.ONLINE.getValue())
                .badgeCount(2)
                .level(Level.BEGINNER.getValue())
                .build();
    }
}
