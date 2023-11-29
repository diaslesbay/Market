package com.example.test.controller;

import com.example.test.exceptions.JwtTokenExpiredException;
import com.example.test.token.dto.RefreshTokenRequestDto;
import com.example.test.repository.AuthenticationRepository;
import com.example.test.token.dto.JwtAuthenticationResponseDto;
import com.example.test.dto.LoginDto;
import com.example.test.dto.RegisterDto;
import com.example.test.validator.SellerValidator;
import com.example.test.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SellerValidator sellerValidator;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        if(registerDto.getRole().equalsIgnoreCase("REGISTERED_USER"))
            userValidator.validate(registerDto, bindingResult);
        else if(registerDto.getRole().equalsIgnoreCase("SELLER"))
            sellerValidator.validate(registerDto, bindingResult);
        else throw new RuntimeException("Not found role");

        if (bindingResult.hasErrors())
            return ResponseEntity.ok(Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));

        return ResponseEntity.ok(String.valueOf(authenticationRepository.register(registerDto)));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authenticationRepository.login(loginDto));
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(
            @RequestBody RefreshTokenRequestDto refreshTokenRequest) throws JwtTokenExpiredException {
        return ResponseEntity.ok(authenticationRepository.refreshToken(refreshTokenRequest));
    }
}
