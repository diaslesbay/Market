package com.example.test.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String cardHoldName;

    @Column(unique = true)
    private String IBAN;

    private String givenTime;
    private String expirationTime;

    private BigDecimal balance;

    @Column(unique = true)
    private Integer CVV;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


}

