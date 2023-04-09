package com.jamilis.login.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GeneralSecurityException.class, UnsupportedEncodingException.class,
            NoSuchAlgorithmException.class})
    public ResponseEntity<Object> handleEncryptException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(Instant.now(), HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
