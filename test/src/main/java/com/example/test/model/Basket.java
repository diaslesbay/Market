package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "baskets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<BasketProduct> basketProducts;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
