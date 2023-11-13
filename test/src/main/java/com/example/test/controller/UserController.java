package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.model.User;
import com.example.test.security.UserDetails;
import com.example.test.service.CategoryService;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @GetMapping("/hello")
    public String seyHello() {
        return "hello";
    }


    @GetMapping("/profile")
    public String profile(@RequestBody User user) {
        System.out.println(11111);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUser());
        return "hello";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }
}
