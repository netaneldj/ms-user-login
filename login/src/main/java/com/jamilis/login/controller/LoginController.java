package com.jamilis.login.controller;

import com.jamilis.login.dto.LoginRequestDto;
import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.exception.UserAlreadyExistException;
import com.jamilis.login.exception.UserNotFoundException;
import com.jamilis.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@RestController
public class LoginController {

    @Autowired
    ILoginService service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpRequestDto dto) throws GeneralSecurityException,
            UnsupportedEncodingException, UserAlreadyExistException {
        SignUpResponseDto responseDto = service.signUpUser(dto);
        return new ResponseEntity<SignUpResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequestDto dto) throws GeneralSecurityException,
            UnsupportedEncodingException, UserNotFoundException {
        LoginResponseDto responseDto = service.loginUser(dto);
        return new ResponseEntity<LoginResponseDto>(responseDto, HttpStatus.OK);
    }

}
