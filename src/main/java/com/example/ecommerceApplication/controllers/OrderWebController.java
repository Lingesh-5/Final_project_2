package com.example.ecommerceApplication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableMethodSecurity
@RequestMapping("/user")
public class OrderWebController {

    @GetMapping("/order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String payment() {
        return "order.html";
    }

    @GetMapping("/userOrder-list")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String userOrderList() {
        return "userOrder-list.html";
    }
    
    
}
