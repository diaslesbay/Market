package com.example.test.model.subclass;

import com.example.test.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "quantityProducts")
public class QuantityProduct {
    @Id
    @Column(name = "productId")
    private Long productId;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;
}
