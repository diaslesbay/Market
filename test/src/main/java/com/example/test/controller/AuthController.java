package com.example.test.controller;

import com.example.test.validator.UserValidator;
import com.example.test.dto.JwtRequest;
import com.example.test.model.User;
import com.example.test.service.AuthService;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public String registration(@RequestBody @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        userService.create(user);
        return "redirect:";
    }
}
