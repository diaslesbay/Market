package com.example.test.model;

import com.example.test.enums.TypeOfProduct;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String description;
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "typeOfProduct")
    @Enumerated(EnumType.STRING)
    private TypeOfProduct typeOfProduct;

    @Column(name = "quantityOrWeight")
    private Long quantityOrWeight;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "category_title")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}

