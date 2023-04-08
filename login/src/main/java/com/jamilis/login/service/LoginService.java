package com.jamilis.login.service;

import com.jamilis.login.dao.IUserRepository;
import com.jamilis.login.dto.LoginRequestDto;
import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.exception.UserAlreadyExistException;
import com.jamilis.login.exception.UserNotFoundException;
import com.jamilis.login.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
public class LoginService implements ILoginService{

    @Autowired
    private IUserRepository repository;

    @Override
    public SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) throws GeneralSecurityException,
            UnsupportedEncodingException, UserAlreadyExistException {
        if (repository.findByEmail(signUpRequestDto.getEmail()).isPresent()) throw new UserAlreadyExistException();
        UserEntity signUpRequestEntity = IUserMapper.INSTANCE.mapToEntity(signUpRequestDto);
        UserEntity userCreated = repository.save(signUpRequestEntity);
        return IUserMapper.INSTANCE.mapToSignUpResponse(userCreated);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) throws GeneralSecurityException,
            UnsupportedEncodingException, UserNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findByToken(loginRequestDto.getToken());
        if (!optionalUserEntity.isPresent()) throw new UserNotFoundException();
        return IUserMapper.INSTANCE.mapToLoginResponse(optionalUserEntity.get());
    }
}
