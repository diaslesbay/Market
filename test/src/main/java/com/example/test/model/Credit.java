package com.example.test.model;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @ManyToOne
    @JoinColumn(name = "cardNumber")
    private Card card;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal monthlyPayment;
}
