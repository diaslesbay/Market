package com.example.test.repository;

import com.example.test.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long>  {
    void deleteBasketProductById(Long id);
//    @Transactional
//    void deleteBasketProductsByBasketProductId(Long basketProductId);
    @Transactional
    @Modifying
    @Query("DELETE FROM BasketProduct bp WHERE bp.basketProductId IN :basketProductIds")
    void deleteByBasketProductIds(List<Long> basketProductIds);
}
