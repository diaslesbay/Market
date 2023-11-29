package com.example.test.repository;

import com.example.test.model.Product;
import com.example.test.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {
    @Query("SELECT p.productName, AVG(r.rating) " +
            "FROM Review r JOIN Product p " +
            "ON r.product.productId = p.productId " +
            "GROUP BY p.productId " +
            "ORDER BY AVG(r.rating) DESC ")
    List<Object[]> findByAllProductWithRating();
}
