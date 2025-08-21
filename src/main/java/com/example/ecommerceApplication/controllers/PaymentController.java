package com.example.ecommerceApplication.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceApplication.pojos.PaymentCreateOrderPayload;
import com.example.ecommerceApplication.pojos.PaymentVerifyPayload;
import com.example.ecommerceApplication.services.PaymentService;

@RestController
@RequestMapping("/rest/payments")
@EnableWebSecurity
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createOrder(@RequestBody PaymentCreateOrderPayload payload) throws Exception {

        long expectedAmount = payload.getAmountInRupees();

        Map<String, Object> order = paymentService.createOrder(expectedAmount, "INR", "rcpt_" + System.currentTimeMillis(), Map.of("userId", payload.getUserId()));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/verify")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentVerifyPayload payload) {

        boolean ok = paymentService.verifyPaymentSignature(payload.getRazorpayOrderId(), payload.getRazorpayPaymentId(), payload.getRazorpaySignature());

        if (!ok) {
            return ResponseEntity.badRequest().body(Map.of("status", "FAILED", "reason", "Invalid signature"));
        }

        paymentService.updatePaid(payload.getRazorpayOrderId(), payload.getRazorpayPaymentId());
        return ResponseEntity.ok(Map.of("status", "PAID"));
    }
}
