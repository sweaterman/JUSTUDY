package com.justudy.backend.login.controller;

import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.login.dto.response.LoginResponse;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest,
                                               HttpServletRequest request) {
        LoginResponse loginInfo = loginService.loginProcess(loginRequest);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginInfo.getLoginSequence());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(loginInfo.getNickname()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
