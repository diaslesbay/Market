package com.example.test.model;

import com.example.test.service.BasketService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "basket_product")
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketProductId;


    @Column(name = "id")
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    @Column(name = "quantity")
    private Long quantity;
}
