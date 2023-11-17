package com.example.test.controller;

import com.example.test.dto.RefreshTokenRequest;
import com.example.test.repository.AuthenticationRepository;
import com.example.test.dto.JwtAuthenticationResponse;
import com.example.test.dto.SignInRequest;
import com.example.test.dto.SignUpRequest;
import com.example.test.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationRepository authenticationService;
    private final UserValidator userValidator;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest signUpRequest, BindingResult bindingResult){
        userValidator.validate(signUpRequest, bindingResult);
        if (bindingResult.hasErrors())
            return Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return String.valueOf(ResponseEntity.ok(authenticationService.signup(signUpRequest)));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
//    private final UserValidator userValidator;
//    private final AuthService authService;
//    private final UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
//        return authService.createAuthToken(authRequest);
//    }
//
//    @PostMapping("/registration")
//    public String registration(@RequestBody @Valid User user, BindingResult bindingResult) {
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors())
//            return Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
//        userService.create(user);
//        return "redirect:";
//    }

}
