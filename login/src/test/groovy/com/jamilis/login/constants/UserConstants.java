package com.jamilis.login.constants;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.utils.EncryptUtils;
import com.jamilis.login.utils.JwtUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.UUID;

public class UserConstants {
    public static final SignUpRequestDto SIGN_UP_REQUEST_DTO = new SignUpRequestDto("Test01", "firsttest@email.com", "T01password",
            PhoneConstants.PHONE_DTO_LIST);

    public static final SignUpRequestDto SIGN_UP_REQUEST_DTO2 = new SignUpRequestDto("Test02", "secondtest@email.com", "T02password",
            PhoneConstants.PHONE_DTO_LIST);
    public static final UserEntity USER_ENTITY;

    static {
        try {
            USER_ENTITY = new UserEntity(UUID.randomUUID().toString(), Instant.now(), Instant.now(), JwtUtils.generateJwt("test01@email.com"), true, "Test01", "test01@email.com", EncryptUtils.encrypt("T01password", "test01@email.com"), PhoneConstants.PHONE_ENTITY_LIST);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
