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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Integer rating;
    private String comment;
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
