package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
}
