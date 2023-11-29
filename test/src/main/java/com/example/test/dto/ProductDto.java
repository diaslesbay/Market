package com.example.test.dto;

import com.example.test.model.Category;
import com.example.test.model.Seller;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
public class ProductDto {
    private String productName;
    private String description;
    private BigDecimal price;
    private String selectTypeOfProduct;
    private Seller seller;
    private Category category;
    private Long quantityOrWeight;
}
