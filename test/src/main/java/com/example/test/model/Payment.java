package com.example.test.model;

import com.example.test.enums.TypeOfPayment;
import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String paymentTime;
    private BigDecimal paymentAmount;

    @Column(name = "typeOfPayment")
    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    private Order orders;


}
