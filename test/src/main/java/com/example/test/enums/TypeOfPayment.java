package com.example.test.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TypeOfPayment {
    CREDIT_CARD("Credit card payment"),
    DEBIT_CARD("Debit card payment");
    private final String message;

}
