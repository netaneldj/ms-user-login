package com.jamilis.login.service

import com.jamilis.login.LoginApplication
import com.jamilis.login.constants.UserConstants
import com.jamilis.login.dto.LoginRequestDto
import com.jamilis.login.exception.UserAlreadyExistException
import com.jamilis.login.exception.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = LoginApplication)
class ILoginServiceTest extends Specification {
    @Autowired
    private LoginService loginService

    def "sign up user ok"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
        when:
        def response = loginService.signUpUser(signUpUserRequestDto)
        then:
        noExceptionThrown()
        with(response) {
            id != null
            created != null
            lastLogin != null
            token != null
            isActive == true
        }

    }

    def "sign up throws user already exist exception"() {
        def signUpRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
        when:
        loginService.signUpUser(signUpRequestDto)
        loginService.signUpUser(signUpRequestDto)
        then:
        thrown(UserAlreadyExistException)
    }

    def "login user ok"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO2
        def signUpUserResponseDto = loginService.signUpUser(signUpUserRequestDto)
        when:
        def loginResponseDto = loginService.loginUser(new LoginRequestDto(signUpUserResponseDto.token));
        then:
        noExceptionThrown()
        with(loginResponseDto) {
            id == signUpUserResponseDto.getId()
            created == signUpUserResponseDto.getCreated()
            lastLogin == signUpUserResponseDto.getLastLogin()
            token == signUpUserResponseDto.getToken()
            isActive == true
            name == signUpUserRequestDto.getName()
            password == signUpUserRequestDto.getPassword()
            phones == signUpUserRequestDto.getPhones()
        }
    }

    def "login throws user not found exception"() {
        given:
        def loginRequestDto = new LoginRequestDto("fakeToken")
        when:
        loginService.loginUser(loginRequestDto);
        then:
        thrown(UserNotFoundException)
    }
}
