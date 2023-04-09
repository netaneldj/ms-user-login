package com.jamilis.login.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.Instant;
import java.util.UUID;

public class JwtUtils {

    public static String generateJwt(String email) {
        try {
            return JWT.create()
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("email", email)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(Algorithm.HMAC256("secret"));
        } catch (JWTCreationException e) {
            throw e;
        }
    }
}
