package com.example.test.model;

import com.example.test.enums.TypeOfPayment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

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

    @Column(name = "typeOfPayment")
    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @OneToMany(mappedBy = "payments")
    private List<Order> orders;


}
