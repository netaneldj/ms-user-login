package com.jamilis.login.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class LoginResponseDto {
    private String id;
    private Instant created;
    private Instant lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String password;
    private List<PhoneDto> phones;
}
