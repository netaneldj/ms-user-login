package com.jamilis.login.controller;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.exception.UserAlreadyExistException;
import com.jamilis.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.GeneralSecurityException;

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
            return new ResponseEntity<UserAlreadyExistException>(e, HttpStatus.NOT_FOUND);
        } catch (GeneralSecurityException e){
            return new ResponseEntity<GeneralSecurityException>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
