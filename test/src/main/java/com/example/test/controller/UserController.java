package com.example.test.controller;

import com.example.test.model.Basket;
import com.example.test.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

;import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final BasketService basketService;


    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserDetails profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
    @PostMapping("/product/toBasket/{id}")
    public Basket toBasket(@RequestParam Long productId){
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return basketService.addToBasketProduct(authentication, productId);
    }
    @DeleteMapping("/product/delete/{id}")
    public Basket deleteProductFromBasket(@RequestParam Long productId){
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return basketService.deleteProductFromBasket(authentication, productId);
    }

    @DeleteMapping("/product/quantity/delete/{id}")
    public Basket deleteProductQuantityFromBasket(@RequestParam Long productId){
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return basketService.deleteProductQuantityFromBasket(authentication, productId);
    }
}
