package com.example.test.repository;

import com.example.test.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Transactional
    Optional<Seller> findByUsername(String username);
    Optional<Seller> findBySellerId(Long sellerId);
    @Query("select p from Product p inner join Seller s on " +
            "p.seller.sellerId = s.sellerId")
    List<Seller> findSellerWithAllProducts();

    Optional<Seller> findByPhoneNumber(String phoneNumber);
    Optional<Seller> findByEmail(String email);
}
