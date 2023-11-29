package com.example.test.validator;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public String dateNow(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    public String expirationDate(){
        LocalDateTime currentDateTime = LocalDateTime.now().plusYears(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return currentDateTime.format(formatter);
    }
    public String creditEndDate(){
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(3);
        System.out.println("before formatter "+currentDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("after formatter "+formatter);

        return currentDateTime.format(formatter);
    }
    public String saleEndDate(){
        LocalDateTime currentDateTime = LocalDateTime.now().plusDays(5);
        System.out.println("before formatter "+currentDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("after formatter "+formatter);

        return currentDateTime.format(formatter);
    }
}
