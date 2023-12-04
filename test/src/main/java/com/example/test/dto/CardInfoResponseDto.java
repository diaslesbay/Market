package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoResponseDto {
    private Long cardId;
    private String cardHoldName;
    private String givenTime;
    private String expirationTime;
    private BigDecimal balance;
    private String iban;
    private Integer cvv;
}
