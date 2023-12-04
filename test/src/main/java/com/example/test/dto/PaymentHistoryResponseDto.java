package com.example.test.dto;

import com.example.test.enums.TypeOfPayment;
import com.example.test.model.Order;
import com.example.test.model.Product;
import com.example.test.model.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryResponseDto {
    private Long paymentId;
    private String paymentTime;
    private BigDecimal paymentAmount;
    private String typeOfPayment;
    private Long orderId;
    private String orderTime;
    private String username;
    private List<Product> products;
}
