package com.example.test.service;

import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllClass(){
        return productRepository.findAll();
    }

}
