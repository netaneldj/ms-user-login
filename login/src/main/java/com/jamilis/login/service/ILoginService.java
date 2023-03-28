package com.jamilis.login.service;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;

public interface ILoginService {
    SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto);
}
