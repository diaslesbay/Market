package com.example.test.enums;

public enum TypeOfPayment {
    CREDIT_CARD("Credit card payment"),
    DEBIT_CARD("Debit card payment");


    private final String message;

    TypeOfPayment(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
