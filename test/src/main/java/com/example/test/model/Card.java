package com.example.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card {
    @Id
    private Long cardNumber;

    private String cardHoldName;

    @Column(unique = true)
    private String IBAN;

    private LocalDate givenDate;
    private LocalDate expirationDate;
    private Integer balance;

    @Column(unique = true)
    private Integer CVV;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}

