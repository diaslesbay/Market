package com.example.test.controller;

import com.example.test.Validator.UserValidator;
import com.example.test.dto.JwtRequest;
import com.example.test.dto.JwtResponse;
import com.example.test.exceptions.AppError;
import com.example.test.model.User;
import com.example.test.security.UserDetails;
import com.example.test.service.AuthService;
import com.example.test.service.UserDetailsService;
import com.example.test.service.UserService;
import com.example.test.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/a")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        System.out.println(11);
      return authService.createAuthToken(authRequest);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/registration")
    public String registration(@RequestBody @Valid User user,  BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        userService.create(user);
        return "redirect:";
    }
}
