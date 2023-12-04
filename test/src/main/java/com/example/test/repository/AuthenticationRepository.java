package com.example.test.repository;

import com.example.test.token.dto.RefreshTokenRequestDto;
import com.example.test.dto.LoginDto;
import com.example.test.dto.RegisterDto;
import com.example.test.token.dto.JwtAuthenticationResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository {
    RegisterDto register(RegisterDto registerDto) ;
    JwtAuthenticationResponseDto login(LoginDto loginDto) throws Exception;
    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest);
}
