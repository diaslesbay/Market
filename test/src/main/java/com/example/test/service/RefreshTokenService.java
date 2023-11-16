package com.example.test.service;


import com.example.test.model.RefreshToken;
import com.example.test.repository.RefreshTokenRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder().user(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600_000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }
    public Optional<RefreshToken> findByToken(String refreshToken){
        return refreshTokenRepository.findRefreshTokenByToken(refreshToken);
    }
    public List<RefreshToken> findRefreshTokensByUser(String username){
        return refreshTokenRepository.findRefreshTokensByUser(username);
    }
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + "Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }
    public void deleteRefreshTokens(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
}
