package com.example.test.dto;

import com.example.test.model.Category;
import com.example.test.model.Seller;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Calendar;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private String description;
    private BigDecimal price;
    private String selectTypeOfProduct;
    private String category;
    private Long quantityOrWeight;
}
