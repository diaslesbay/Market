package com.example.test.controller;

import com.example.test.dto.ProductDto;
import com.example.test.model.Product;
import com.example.test.model.Seller;
import com.example.test.service.ProductService;
import com.example.test.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final ProductService productService;
    @GetMapping("/all")
    public List<Object> sellers() {
        return sellerService.getAllSeller();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@ModelAttribute ProductDto productDto, @RequestParam MultipartFile image) throws IOException {
        return productService.save(productDto, image);
    }
}
