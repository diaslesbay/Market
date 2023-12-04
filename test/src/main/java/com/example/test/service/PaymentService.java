package com.example.test.service;

import com.example.test.dto.PaymentHistoryResponseDto;
import com.example.test.enums.ErrorMessage;
import com.example.test.exceptions.ServiceException;
import com.example.test.model.Payment;
import com.example.test.model.User;
import com.example.test.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }


    public List<PaymentHistoryResponseDto> getPaymentHistory(UserDetails userDetails){
        User user = userService.findByUsername(userDetails.getUsername());

        List<Payment> paymentList = paymentRepository.findPaymentByUserId(user.getUserId());
        if(paymentList.isEmpty())
            throw new ServiceException(
                    ErrorMessage.LIST_IS_EMPTY.getMessage(),
                    ErrorMessage.LIST_IS_EMPTY.getStatus()
            );

        return paymentList.stream()
                .map(payment ->
                        new PaymentHistoryResponseDto(
                                payment.getPaymentId(),
                                payment.getPaymentTime(),
                                payment.getPaymentAmount(),
                                payment.getTypeOfPayment().name(),
                                payment.getOrders().getOrderId(),
                                payment.getOrders().getOrderTime(),
                                payment.getOrders().getUser().getUsername(),
                                payment.getOrders().getProducts()
                        ))
                .collect(Collectors.toList());
    }
}
