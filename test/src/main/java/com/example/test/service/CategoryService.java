package com.example.test.service;

import com.example.test.enums.ErrorMessage;
import com.example.test.exceptions.ServiceException;
import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllClass(){
        return categoryRepository.findAll();
    }

    public Category getByCategoryTitle(String categoryName){
        return categoryRepository.getByCategoryTitle(categoryName).orElseThrow(()->
                new ServiceException(
                        String.format(ErrorMessage.CATEGORY_IS_NOT_FOUND.getMessage(), categoryName),
                        ErrorMessage.CATEGORY_IS_NOT_FOUND.getStatus()
                )
        );
    }
}
