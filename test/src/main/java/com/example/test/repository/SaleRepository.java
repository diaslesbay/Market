package com.example.test.repository;

import com.example.test.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findSaleByProductProductId(Long productId);

    @Transactional
    @Modifying
    void deleteSaleByProductProductId(Long productId);
}
