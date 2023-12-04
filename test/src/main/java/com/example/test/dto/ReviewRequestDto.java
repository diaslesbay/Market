package com.example.test.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private Long productId;
    private String comment;
    private Integer rate;
}
