package com.justudy.backend.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.login.service.LoginService;
import com.justudy.backend.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .build();
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {
        //given
        final String USER_ID = "test";
        final String PASSWORD = "ssafy";

        LoginRequest request = new LoginRequest(USER_ID, PASSWORD);
        String json = objectMapper.writeValueAsString(request);

        BDDMockito.given(loginService.loginProcess(request))
                .willReturn(1L);


        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}