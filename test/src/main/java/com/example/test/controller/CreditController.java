package com.example.test.controller;

import com.example.test.dto.CreditResponseDto;
import com.example.test.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;
    @GetMapping("/show-credits")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditResponseDto> showCredits(@AuthenticationPrincipal UserDetails userDetails){
        return creditService.getCreditsByUserId(userDetails);
    }
}
