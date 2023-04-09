package com.jamilis.login.service

import com.jamilis.login.LoginApplication
import com.jamilis.login.dto.LoginRequestDto
import com.jamilis.login.dto.PhoneDto
import com.jamilis.login.dto.SignUpRequestDto
import com.jamilis.login.exception.UserAlreadyExistException
import com.jamilis.login.exception.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = LoginApplication)
class ILoginServiceTest extends Specification {
    @Autowired
    private LoginService loginService

    def "sign up user ok"(){
        given:
        def signUpUserRequestDto = new SignUpRequestDto("Test01", "firsttest@email.com", "T01password", [new PhoneDto(12345678, 11, "AR")])
        when:
        def response = loginService.signUpUser(signUpUserRequestDto)
        then:
        noExceptionThrown()
        with(response){
            id != null
            created != null
            lastLogin != null
            token != null
            isActive == true
        }

    }

    def "sign up throws user already exist exception"(){
        def signUpRequestDto = new SignUpRequestDto("Test02", "secondtest@email.com", "a2asfGfdfdf4", [new PhoneDto(123L, 456, "AR")])
        when:
        loginService.signUpUser(signUpRequestDto)
        loginService.signUpUser(signUpRequestDto)
        then:
        thrown(UserAlreadyExistException)
    }

    def "login user ok" (){
        given:
        def signUpUserRequestDto = new SignUpRequestDto("name", "test7@mail.com", "a2asfGfdfdf4", [new PhoneDto(123L, 456, "AR")])
        def signUpUserResponseDto = loginService.signUpUser(signUpUserRequestDto)
        when:
        def loginResponseDto = loginService.loginUser(new LoginRequestDto(signUpUserResponseDto.token));
        then:
        noExceptionThrown()
        with(loginResponseDto){
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

    def "login throws user not found exception" (){
        given:
        def loginRequestDto = new LoginRequestDto("fakeToken")
        when:
        loginService.loginUser(loginRequestDto);
        then:
        thrown(UserNotFoundException)
    }
}
