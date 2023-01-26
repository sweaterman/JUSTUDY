package com.justudy.backend.login.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("userId") String userId,
                        @JsonProperty("password") String password) {
        this.userId = userId;
        this.password = password;
    }
}
