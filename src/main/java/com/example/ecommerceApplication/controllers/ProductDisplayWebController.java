package com.example.ecommerceApplication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@EnableMethodSecurity
public class ProductDisplayWebController {
    
    @GetMapping("/product-list")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String productList() {
        return "product-list.html";
    }

    @GetMapping("/cart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String cart() {
        return "cart.html";
    }
    
    @GetMapping("/profile")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String profile() {
        return "profile.html";
    }
    
    

}
