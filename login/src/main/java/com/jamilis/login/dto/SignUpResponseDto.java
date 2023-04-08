package com.jamilis.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private String id;
    private Instant created;
    private Instant lastLogin;
    private String token;
    private Boolean isActive;
}
