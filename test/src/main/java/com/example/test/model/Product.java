package com.example.test.model;

import com.example.test.enums.WeightOrQuantity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String description;
    private BigDecimal price;

    @Column(name = "weightOrQuantity")
    @Enumerated(EnumType.STRING)
    private WeightOrQuantity weightOrQuantity;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller sellerId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "products")
    private List<OrderProduct> products;

}

