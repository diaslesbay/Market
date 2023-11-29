package com.example.test.service;

import com.example.test.enums.TypeOfPayment;
import com.example.test.model.Payment;
import com.example.test.model.User;
import com.example.test.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment getPaymentHistory(UserDetails userDetails){
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(()->
        {
            throw new UsernameNotFoundException("User not found");
        });

        return paymentRepository.findPaymentByUserId(user.getUserId());
    }
}
