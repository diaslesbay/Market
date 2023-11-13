package com.example.test.model.subclass;

import com.example.test.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.math.BigDecimal;

@Data
@Entity
@Getter
@Setter
@Table(name = "weightProduct")
public class WeightProduct {
    @Id
    @Column(name = "productId")
    private Long productId;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    private Product product;

    private BigDecimal weight;
}
