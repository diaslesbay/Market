package com.example.test.service;

import com.example.test.model.Seller;
import com.example.test.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    public List<Object> getAllSeller(){
        return sellerRepository.getAllSellerWithProducts();
    }

    public Optional<Seller> getBySellerId(Long sellerId){
        return sellerRepository.findBySellerId(sellerId);
    }

    public List<Seller> findAll(){
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerByUsername(String username){
        return sellerRepository.findByUsername(username);
    }
    public Optional<Seller> getSellerByEmail(String email){
        return sellerRepository.findByEmail(email);
    }

    public Optional<Seller> getSellerByPhoneNumber(String phoneNumber){
        return sellerRepository.findByPhoneNumber(phoneNumber);
    }
    public void save(Seller seller){
        sellerRepository.save(seller);
    }
}
