package com.example.test.model;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @ManyToOne
    @JoinColumn(name = "cardNumber")
    private Card card;

    private String startTime;
    private String endTime;

    private BigDecimal monthlyPayment;


}
