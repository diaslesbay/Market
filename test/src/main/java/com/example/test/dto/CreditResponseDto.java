package com.example.test.dto;

import com.example.test.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditResponseDto {
    private Long creditId;

    private String iban;

    private String startTime;
    private String endTime;

    private BigDecimal monthlyPayment;
}
