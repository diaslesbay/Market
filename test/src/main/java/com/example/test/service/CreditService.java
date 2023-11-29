package com.example.test.service;

import com.example.test.model.Credit;
import com.example.test.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;
    public Credit save(Credit credit){
        return creditRepository.save(credit);
    }
}
