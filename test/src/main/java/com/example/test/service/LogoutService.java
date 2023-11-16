package com.example.test.service;

import com.example.test.model.RefreshToken;
import com.example.test.model.Token;
import com.example.test.repository.RefreshTokenRepository;
import com.example.test.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        System.out.println("logout");
        if(authHeader == null || !authHeader.startsWith("Bearer "))
            return;
        jwt = authHeader.substring(7);
        RefreshToken storedToken = refreshTokenRepository.findRefreshTokenByToken(jwt).orElse(null);
        Token token = tokenRepository.findTokensByUser(jwt).orElse(null);

        if(storedToken != null){
            refreshTokenRepository.delete(storedToken);
        }
        if(token != null){
            tokenRepository.delete(token);
        }


    }
}
