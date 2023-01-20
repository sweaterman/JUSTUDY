package com.justudy.backend.login.controller;

import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Validated LoginRequest request) {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
