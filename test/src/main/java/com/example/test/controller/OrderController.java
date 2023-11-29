package com.example.test.controller;

import com.example.test.model.Basket;
import com.example.test.model.Order;
import com.example.test.model.User;
import com.example.test.service.BasketService;
import com.example.test.service.OrderService;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/addOrder")
    public Order addOrder(String selectTypeOfPayment){
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(authentication.getUsername());
        return orderService.addOrder(authentication, selectTypeOfPayment);
    }
}
