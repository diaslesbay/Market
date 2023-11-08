package com.example.test.enums;

public enum TypeOfPayment {
    CASH("Payment by cash"),
    CARD("Payment by card"),
    CREDIT_CARD("Credit card payment"),
    DEBIT_CARD("Debit card payment"),
    ONLINE_PAYMENT_BY_CARD("Online payment by card");


    private final String message;

    TypeOfPayment(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
