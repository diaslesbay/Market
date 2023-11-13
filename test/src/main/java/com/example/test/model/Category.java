package com.example.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    private String categoryTitle;

    @Column(unique = true)
    private String categoryName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

}
