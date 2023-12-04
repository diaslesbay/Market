package com.example.test.dto;

import lombok.*;

@Getter
@Setter
public class ReviewRequestDto {
    private Long productId;
    private String comment;
    private Integer rate;
}
