package com.example.test.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    String accessToken;
    String refreshToken;
}
