package com.jamilis.login.controller;

import com.jamilis.login.dto.*;
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
import java.time.Instant;

@RestController
public class LoginController {

    @Autowired
    ILoginService service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpRequestDto dto) {
        try {
            SignUpResponseDto responseDto = service.signUpUser(dto);
            return new ResponseEntity<SignUpResponseDto>(responseDto, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<ErrorDto>(new ErrorDto(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                    e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (GeneralSecurityException | UnsupportedEncodingException e){
            return new ResponseEntity<ErrorDto>(new ErrorDto(Instant.now(), HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage()), HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequestDto dto) {
        try {
            LoginResponseDto responseDto = service.loginUser(dto);
            return new ResponseEntity<LoginResponseDto>(responseDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<ErrorDto>(new ErrorDto(Instant.now(), HttpStatus.NOT_FOUND.value(),
                    e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (GeneralSecurityException | UnsupportedEncodingException e){
            return new ResponseEntity<ErrorDto>(new ErrorDto(Instant.now(), HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}
