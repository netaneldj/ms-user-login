package com.jamilis.login.mapper

import com.jamilis.login.dto.PhoneDto
import com.jamilis.login.dto.SignUpRequestDto
import com.jamilis.login.entity.PhoneEntity
import com.jamilis.login.entity.UserEntity
import com.jamilis.login.utils.EncryptUtils
import com.jamilis.login.utils.JwtUtils
import spock.lang.Specification

import java.time.Instant

class IUserMapperTest extends Specification {
    def "MapToEntity"() {
        given:
        def signUpRequestDto = new SignUpRequestDto("Test01", "firsttest@email.com", "T01password",
                [new PhoneDto(12345678, 11, "AR")])
        when:
        def userEntity = IUserMapper.INSTANCE.mapToEntity(signUpRequestDto)
        then:
        userEntity.getId() != null
        userEntity.getCreated() != null
        userEntity.getLastLogin() != null
        userEntity.getToken() != null
        userEntity.getIsActive() == true
        userEntity.getName() == signUpRequestDto.getName()
        userEntity.getEmail() == signUpRequestDto.getEmail()
        userEntity.getPassword() == EncryptUtils.encrypt(signUpRequestDto.getPassword(), signUpRequestDto.getEmail())
        userEntity.getPhones().size() == signUpRequestDto.getPhones().size()
    }

    def "MapToSignUpResponse"() {
        given:
        def userEntity = new UserEntity(UUID.randomUUID().toString(), Instant.now(), Instant.now(), JwtUtils.generateJwt("test01@email.com"), true, "Test01", "test01@email.com", EncryptUtils.encrypt("T01password", "test01@email.com"), [new PhoneEntity(1, UUID.randomUUID().toString(), 23456789, 11, "AR")])
        when:
        def signUpResponseDto = IUserMapper.INSTANCE.mapToSignUpResponse(userEntity)
        then:
        signUpResponseDto.getId() == userEntity.getId()
        signUpResponseDto.getCreated() == userEntity.getCreated()
        signUpResponseDto.getLastLogin() == userEntity.getLastLogin()
        signUpResponseDto.getToken() == userEntity.getToken()
        signUpResponseDto.getIsActive() == userEntity.getIsActive()
    }

    def "MapToLoginResponse"() {
        given:
        def userEntity = new UserEntity(UUID.randomUUID().toString(), Instant.now(), Instant.now(), JwtUtils.generateJwt("test01@email.com"), true, "Test01", "test01@email.com", EncryptUtils.encrypt("T01password", "test01@email.com"), [new PhoneEntity(1, UUID.randomUUID().toString(), 23456789, 11, "AR")])
        when:
        def loginResponseDto = IUserMapper.INSTANCE.mapToLoginResponse(userEntity)
        then:
        loginResponseDto.getId() == userEntity.getId()
        loginResponseDto.getCreated() == userEntity.getCreated()
        loginResponseDto.getLastLogin() == userEntity.getLastLogin()
        loginResponseDto.getToken() == userEntity.getToken()
        loginResponseDto.getIsActive() == userEntity.getIsActive()
        loginResponseDto.getName() == userEntity.getName()
        loginResponseDto.getPassword() == EncryptUtils.decrypt(userEntity.getPassword(), userEntity.getEmail())
        loginResponseDto.getPhones().size() == userEntity.getPhones().size()
    }
}
