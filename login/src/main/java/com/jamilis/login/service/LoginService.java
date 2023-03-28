package com.jamilis.login.service;

import com.jamilis.login.dao.IUserRepository;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    @Autowired
    private IUserRepository repository;

    @Override
    public SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) {
        UserEntity signUpRequestEntity = IUserMapper.INSTANCE.mapToEntity(signUpRequestDto);
        UserEntity userCreated = repository.save(signUpRequestEntity);
        return IUserMapper.INSTANCE.mapToSignUpResponse(userCreated);
    }
}
