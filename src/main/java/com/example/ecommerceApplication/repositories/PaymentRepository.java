package com.example.ecommerceApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerceApplication.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Transactional
    @Modifying
    @Query("update Payment p set p.status='PAID', p.paymentId=:paymentId where p.orderId=:orderId and p.status<>'PAID'")
    void markPaid(String orderId, String paymentId);

}
