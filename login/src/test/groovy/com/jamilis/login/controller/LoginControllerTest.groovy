package com.jamilis.login.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jamilis.login.LoginApplication
import com.jamilis.login.constants.UserConstants
import com.jamilis.login.dto.LoginRequestDto
import com.jamilis.login.dto.LoginResponseDto
import com.jamilis.login.dto.SignUpResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import java.time.temporal.ChronoUnit

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [LoginApplication])
class LoginControllerTest extends spock.lang.Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "sign up user valid"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
        when:
        def response = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/sign-up")
                .content(objectMapper.writeValueAsString(signUpUserRequestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .response
                .contentAsString, SignUpResponseDto)

        then:
        with(response) {
            id != null
            created != null
            lastLogin != null
            token != null
            isActive == true
        }
    }

    def "login user"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO2
        def signUpResponse = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/sign-up")
                .content(objectMapper.writeValueAsString(signUpUserRequestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .response
                .contentAsString, SignUpResponseDto)
        def loginRequestDto = new LoginRequestDto(signUpResponse.getToken())
        when:
        def response = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .content(objectMapper.writeValueAsString(loginRequestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .response
                .contentAsString, LoginResponseDto)
        then:
        with(response) {
            id == signUpResponse.getId()
            created.truncatedTo(ChronoUnit.SECONDS) == signUpResponse.getCreated().truncatedTo(ChronoUnit.SECONDS)
            lastLogin != signUpResponse.getLastLogin()
            token != signUpResponse.getToken()
            isActive == true
            name == signUpUserRequestDto.getName()
            password == signUpUserRequestDto.getPassword()
            phones == signUpUserRequestDto.getPhones()
        }
    }
}
