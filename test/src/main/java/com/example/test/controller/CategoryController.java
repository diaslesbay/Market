package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<String> categories() {
        return categoryService.getAllClass()
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }
}
