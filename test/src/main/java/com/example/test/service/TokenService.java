package com.example.test.service;

import com.example.test.model.Token;
import com.example.test.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    public Optional<Token> findTokensByUser(String username){
        return tokenRepository.findTokensByUser(username);
    }
    public void deleteTokens(Token token){
        tokenRepository.delete(token);
    }

    public Token save(Token token){
        return tokenRepository.save(token);
    }
}
