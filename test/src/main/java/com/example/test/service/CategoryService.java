package com.example.test.service;

import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllClass(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getByCategoryTitle(String categoryName){
        return categoryRepository.getByCategoryTitle(categoryName);
    }
}
