package com.example.test.controller;

import com.example.test.dto.ProductDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> products() {
        return productService.getAllClass();
    }
    @GetMapping("/{category}")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        System.out.println(category);
        return productService.getByCategory(category);
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductsByProductId(@RequestParam Long id) {
        return productService.getProductsByProductId(id);
    }
}
