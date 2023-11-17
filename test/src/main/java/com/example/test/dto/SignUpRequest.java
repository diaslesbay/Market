package com.example.test.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
}
