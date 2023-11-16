package com.example.test.service;

import com.example.test.dto.JwtRequest;
import com.example.test.dto.JwtResponse;
import com.example.test.exceptions.AppError;
import com.example.test.model.RefreshToken;
import com.example.test.model.Token;
import com.example.test.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final TokenService tokenService;

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            List<RefreshToken> expiredTokens = new ArrayList<>(refreshTokenService.findRefreshTokensByUser(jwtRequest.getUsername()));

            if(!expiredTokens.isEmpty())
                expiredTokens.forEach(refreshTokenService::deleteRefreshTokens);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(jwtRequest.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

            List<com.example.test.model.Token> tokens =  tokenService.findTokensByUser(userDetails.getUsername())
                    .stream()
                    .collect(Collectors.toList());

            if(!tokens.isEmpty())
                tokens.forEach(tokenService::deleteTokens);

            String token = jwtTokenUtils.generateToken(userDetails);

            com.example.test.model.Token token1 = new Token();
            token1.setAccessToken(token);
            token1.setUser(userService.findByUsername(userDetails.getUsername()).get());
            tokenService.save(token1);

            return ResponseEntity.ok(new JwtResponse(token, refreshToken.getToken()));
        }
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
    }

}
