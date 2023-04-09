package com.jamilis.login.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jamilis.login.LoginApplication
import com.jamilis.login.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [LoginApplication])
class LoginControllerTest extends spock.lang.Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "sign up user valid"() {
        given:
        def signUpUserRequestDto = new SignUpRequestDto("Test01", "firsttest@email.com", "T01password",
                [new PhoneDto(12345678, 11, "AR")])
        when:
        def response = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/sign-up")
                .content(objectMapper.writeValueAsString(signUpUserRequestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .response
                .contentAsString, SignUpResponseDto)

        then:
        with(response){
            id != null
            created != null
            lastLogin != null
            token != null
            isActive == true
        }
    }

    def "login user"() {
        given:
        def signUpUserRequestDto = new SignUpRequestDto("Test02", "secondtest@email.com", "T02password",
                [new PhoneDto(23456789, 11, "AR")])
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
        with(response){
            id == signUpResponse.getId()
            created == signUpResponse.getCreated()
            lastLogin == signUpResponse.getLastLogin()
            token == signUpResponse.getToken()
            isActive == true
            name == signUpUserRequestDto.getName()
            password == signUpUserRequestDto.getPassword()
            phones == signUpUserRequestDto.getPhones()
        }
    }
}
