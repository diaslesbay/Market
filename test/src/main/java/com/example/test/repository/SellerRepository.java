package com.example.test.repository;

import com.example.test.model.Seller;
import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findBySellerId(Long sellerId);
    @Query("select  p  from Seller s inner join Product p on " +
            "s.sellerId = p.seller.sellerId")
    List<Object> getAllSellerWithProducts();

    Optional<Seller> findByUsername(String username);
    Optional<Seller> findByPhoneNumber(String phoneNumber);
    Optional<Seller> findByEmail(String email);
}
