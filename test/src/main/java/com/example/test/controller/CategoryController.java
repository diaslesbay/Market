package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    public List<String> categories() {
        return categoryService.getAllClass()
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }


}
