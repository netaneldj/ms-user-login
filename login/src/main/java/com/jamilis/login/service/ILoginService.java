package com.jamilis.login.service;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.exception.UserAlreadyExistException;

import java.security.GeneralSecurityException;

public interface ILoginService {
    SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) throws GeneralSecurityException, UserAlreadyExistException;
}
