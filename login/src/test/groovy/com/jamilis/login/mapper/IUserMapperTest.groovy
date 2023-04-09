package com.jamilis.login.mapper

import com.jamilis.login.constants.UserConstants
import com.jamilis.login.utils.EncryptUtils
import spock.lang.Specification

class IUserMapperTest extends Specification {
    def "MapToEntity"() {
        given:
        def signUpRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
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
        def userEntity = UserConstants.USER_ENTITY
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
        def userEntity = UserConstants.USER_ENTITY
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
