package com.example.test.service;

import com.example.test.enums.ErrorMessage;
import com.example.test.exceptions.ServiceException;
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

    public Category getByCategoryTitle(String categoryName){
        return categoryRepository.getByCategoryTitle(categoryName).orElseThrow(()->
                new ServiceException(
                        String.format(ErrorMessage.CATEGORY_IS_NOT_FOUND.getMessage(), categoryName),
                        ErrorMessage.CATEGORY_IS_NOT_FOUND.getStatus()
                )
        );
    }
}
