package com.example.ecommerceApplication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@EnableMethodSecurity
public class ProductWebController {

    @GetMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public String product() {
        return "product.html";
    }

    @GetMapping("/order-list")
    @PreAuthorize("hasRole('ADMIN')")
    public String orderList() {
        return "order-list.html";
    }
    
    
}
