package com.example.test.repository;

import com.example.test.dto.RefreshTokenRequest;
import com.example.test.dto.SignInRequest;
import com.example.test.dto.SignUpRequest;
import com.example.test.model.User;
import com.example.test.dto.JwtAuthenticationResponse;

public interface AuthenticationRepository {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
