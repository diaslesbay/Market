package com.example.test.model;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
>>>>>>> b/main
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
<<<<<<< HEAD
@Table(name = "mainCategory")
public class MainCategory {

    @Id
    private String mainCategoryTitle;

=======
@Table(name = "categories")
public class Category {
    @Id
    private Long categoryId;
>>>>>>> b/main
}
