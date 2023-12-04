package com.example.test.model;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardNumber")
    private Card card;

    private String startTime;
    private String endTime;

    private BigDecimal monthlyPayment;


}
