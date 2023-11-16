package com.example.test.controller;

import com.example.test.dto.JwtRequest;
import com.example.test.dto.JwtResponse;
import com.example.test.dto.RefreshTokenRequest;
import com.example.test.model.RefreshToken;
import com.example.test.model.Token;
import com.example.test.service.*;
import com.example.test.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private  final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    @GetMapping("/hello")
    public String seyHello() {
        return "hello";
    }

    @PostMapping("/profile")
    public String profile(Principal principal, @RequestBody JwtRequest jwtRequest) {
        if(!principal.getName().equals(jwtRequest.getUsername())) return HttpStatus.UNAUTHORIZED.name();
        return userService.findByUsername(principal.getName()).toString();
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    List<Token> tokens =  tokenService.findTokensByUser(user.getUsername())
                            .stream()
                            .collect(Collectors.toList());

                    if(!tokens.isEmpty())
                        tokens.forEach(tokenService::deleteTokens);
                    String accessToken = jwtTokenUtils.generateToken(userDetailsService.loadUserByUsername(user.getUsername()));

                    com.example.test.model.Token token1 = new Token();
                    token1.setAccessToken(accessToken);
                    token1.setUser(userService.findByUsername(user.getUsername()).get());
                    tokenService.save(token1);

                    return ResponseEntity.ok(new JwtResponse(accessToken, refreshTokenRequest.getRefreshToken()));
                }).orElseThrow(()-> new RuntimeException("Refresh token is not in database"));
    }
    @GetMapping("/admin")
    public String showAdminPage() {return "admin";}

}
