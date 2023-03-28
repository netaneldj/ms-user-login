package com.jamilis.login.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SignUpResponseDto {
    private String id;
    private Instant created;
    private Instant lastLogin;
    private String token;
    private Boolean isActive;
}
