package com.example.ecommerceApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceApplication.pojos.OrderPayload;
import com.example.ecommerceApplication.services.OrderService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user/rest")
@EnableMethodSecurity
@Validated
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/save-order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> saveOrderDetails(@Valid @RequestBody OrderPayload payload) {
        return ResponseEntity.ok(orderService.saveOrder(payload));
    }
 
    @PostMapping("/get-order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getAllOrderDetails(@RequestBody OrderPayload payload) {
        return ResponseEntity.ok(orderService.getAllOrder(payload));
    }
    
    
}
