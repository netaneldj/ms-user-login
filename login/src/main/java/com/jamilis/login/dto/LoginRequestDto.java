package com.jamilis.login.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {
    @NotNull
    @NotEmpty
    private String token;
}
