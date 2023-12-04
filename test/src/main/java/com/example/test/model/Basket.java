package com.example.test.model;

import lombok.*;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "baskets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @OneToMany(mappedBy = "id")
    private List<BasketProduct> basketProducts;

    @OneToOne
    private User user;
}
