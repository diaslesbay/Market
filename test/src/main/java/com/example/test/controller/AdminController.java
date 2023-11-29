package com.example.test.controller;

import com.example.test.service.SellerService;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final SellerService sellerService;
    @GetMapping("/users")
    public List<Map<String, String>> seeAllUsers(){
        return userService.findAll().stream()
                .filter(user -> !"ADMIN".equals(user.getStatus().name()))
                .map(user -> Map.of(
                        "id", String.valueOf(user.getUserId()),
                        "username", user.getUsername(),
                        "email", user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/sellers")
    public List<Map<String, String>> seeAllSellers(){
        return sellerService.findAll().stream()
                .map(seller -> Map.of(
                        "id", String.valueOf(seller.getSellerId()),
                        "name",seller.getSellerName(),
                        "phone", seller.getPhoneNumber()
                ))
                .collect(Collectors.toList());
    }

}
