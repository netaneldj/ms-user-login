package com.jamilis.login.controller;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    ILoginService service;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody @Valid SignUpRequestDto dto) {
        SignUpResponseDto responseDto = service.signUpUser(dto);
        return new ResponseEntity<SignUpResponseDto>(responseDto, HttpStatus.CREATED);
    }

}
