package com.example.test.model;

import com.example.test.enums.WeightOrQuantity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId")
    private Seller seller;


    @ManyToOne
    @JoinColumn(name = "categoryTitle")
    private Category categoryTitle;


    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}

