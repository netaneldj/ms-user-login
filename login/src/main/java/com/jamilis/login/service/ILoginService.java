package com.jamilis.login.service;

import com.jamilis.login.dto.LoginRequestDto;
import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.exception.UserAlreadyExistException;
import com.jamilis.login.exception.UserNotFoundException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface ILoginService {
    SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) throws GeneralSecurityException,
            UserAlreadyExistException, UnsupportedEncodingException;

    LoginResponseDto loginUser(LoginRequestDto loginRequestDto) throws GeneralSecurityException,
            UserNotFoundException, UnsupportedEncodingException;
}
