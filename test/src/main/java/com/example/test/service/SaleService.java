package com.example.test.service;

import com.example.test.model.Sale;
import com.example.test.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService  {
    private final SaleRepository saleRepository;

    public Optional<Sale> getSaleByProduct(Long productId){
        return saleRepository.findSaleByProductProductId(productId);
    }
    public void save(Sale sale){
        saleRepository.save(sale);

    }
    @Transactional
    public void deleteByProductId(Long productId){
        saleRepository.deleteSaleByProductProductId(productId);
    }

}
