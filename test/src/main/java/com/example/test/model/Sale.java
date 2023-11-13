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
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    private Double percentage;
    private LocalDate startDate;
    private LocalDate endDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;
}
