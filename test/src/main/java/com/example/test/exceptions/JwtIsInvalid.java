package com.example.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JwtIsInvalid extends ResponseStatusException {
    public JwtIsInvalid(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
