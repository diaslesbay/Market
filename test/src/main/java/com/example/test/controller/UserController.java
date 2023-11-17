package com.example.test.controller;

import com.example.test.dto.SignInRequest;
import com.example.test.service.impl.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailService userService;
    @GetMapping("/hello")
    public String seyHello() {
        return "Hello user";
    }
    @PostMapping("/profile")
    public String profile(Principal principal, @RequestBody SignInRequest signInRequest) {
        if(!principal.getName().equals(signInRequest.getUsername())) return HttpStatus.UNAUTHORIZED.name();
        return userService.userDetailsService().loadUserByUsername(principal.getName()).toString();
    }
}
