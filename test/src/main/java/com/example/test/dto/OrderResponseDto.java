package com.example.test.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    private Long orderId;
    private String orderTime;
    private BigDecimal totalAmount;
    private String username;
    private List<ProductResponseDto> products;
}
