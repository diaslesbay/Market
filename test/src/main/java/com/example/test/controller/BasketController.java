package com.example.test.controller;

import com.example.test.dto.BasketResponseDto;
import com.example.test.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    @GetMapping("/show-my-basket")
    @ResponseStatus(HttpStatus.OK)
    public BasketResponseDto showMyBasket(@AuthenticationPrincipal UserDetails userDetails){
        return basketService.getBasket(userDetails);
    }
}
