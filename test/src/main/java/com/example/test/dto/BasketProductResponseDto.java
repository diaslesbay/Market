package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProductResponseDto {
    private Long basketProductId;
    private String productName;
    private BigDecimal price;
    private String description;
    private String sellerUsername;
    private String sellerPhoneNumber;
    private Long quantity;
}
