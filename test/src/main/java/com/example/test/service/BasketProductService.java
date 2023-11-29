package com.example.test.service;

import com.example.test.model.BasketProduct;
import com.example.test.repository.BasketProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketProductService {
    private final BasketProductRepository basketProductRepository;
    public BasketProduct save(BasketProduct basketProduct){
        return  basketProductRepository.save(basketProduct);
    }



    @Transactional
    public void deleteBasketId(List<Long> basketProductIds){
        log.info("From repository: Delete productId = "+basketProductIds);
        basketProductRepository.deleteByBasketProductIds(basketProductIds);
    }
}
