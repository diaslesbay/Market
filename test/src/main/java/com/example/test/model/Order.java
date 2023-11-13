package com.example.test.model;

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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payments;


    @ManyToMany
    @JoinTable(
        name = "orderProducts",
        joinColumns = @JoinColumn(name = "orderId"),
        inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private List<Product> products;
}
