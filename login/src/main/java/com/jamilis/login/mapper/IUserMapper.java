package com.jamilis.login.mapper;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Mapper(uses = IPhoneMapper.class)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper( IUserMapper.class );

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "lastLogin", expression = "java(java.time.Instant.now())")
    @Mapping(target = "token", expression = "java(com.jamilis.login.utils.JwtUtils.generateJwt(dto.getEmail()))")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "password", expression = "java(com.jamilis.login.utils.EncryptUtils.encrypt(dto.getPassword(), dto.getEmail()))")
    UserEntity mapToEntity(SignUpRequestDto dto) throws GeneralSecurityException, UnsupportedEncodingException;

    SignUpResponseDto mapToSignUpResponse(UserEntity entity);

    @Mapping(target = "password", expression = "java(com.jamilis.login.utils.EncryptUtils.decrypt(entity.getPassword(), entity.getEmail()))")
    LoginResponseDto mapToLoginResponse(UserEntity entity) throws GeneralSecurityException, UnsupportedEncodingException;
}
