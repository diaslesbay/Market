package com.example.test.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private LocalDate paymentDate;
    private BigDecimal paymentAmount;

    @OneToMany(mappedBy = "payments")
    private List<Order> products;


}
