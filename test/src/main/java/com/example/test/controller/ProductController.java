package com.example.test.controller;

import com.example.test.model.Product;
import com.example.test.model.RefreshToken;
import com.example.test.service.ProductService;
import com.example.test.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<String> categories() {
        return productService.getAllClass()
                .stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
    }

}
