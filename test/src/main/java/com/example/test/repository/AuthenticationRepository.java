package com.example.test.repository;

import com.example.test.exceptions.JwtTokenExpiredException;
import com.example.test.token.dto.RefreshTokenRequestDto;
import com.example.test.dto.LoginDto;
import com.example.test.dto.RegisterDto;
import com.example.test.model.User;
import com.example.test.token.dto.JwtAuthenticationResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository {
    Object register(RegisterDto registerDto) ;
    JwtAuthenticationResponseDto login(LoginDto loginDto);
    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) throws JwtTokenExpiredException;
}
