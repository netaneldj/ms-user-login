package com.jamilis.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorDto {
    private Instant timestamp;
    private Integer codigo;
    private String detail;
}
