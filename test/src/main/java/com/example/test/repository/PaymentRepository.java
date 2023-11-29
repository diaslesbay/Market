package com.example.test.repository;

import com.example.test.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p " +
            "where p.orders.user.userId = :userId")
    Payment findPaymentByUserId(Long userId);
}
