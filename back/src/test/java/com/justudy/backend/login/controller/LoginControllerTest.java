package com.justudy.backend.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.login.dto.response.LoginResponse;
import com.justudy.backend.login.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        final String NICKNAME = "닉네임";

        LoginRequest request = new LoginRequest(USER_ID, PASSWORD);
        String json = objectMapper.writeValueAsString(request);

        LoginResponse response = new LoginResponse(NICKNAME);
        response.setLoginSequence(100L);
        response.setPassword("1234");

        BDDMockito.given(loginService.loginProcess(request))
                .willReturn(response);


        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}