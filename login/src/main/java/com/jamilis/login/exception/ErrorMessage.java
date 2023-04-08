package com.jamilis.login.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private Instant timestamp;
    private Integer codigo;
    private String detail;
}
