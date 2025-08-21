package com.example.ecommerceApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceApplication.pojos.CartPayload;
import com.example.ecommerceApplication.pojos.UserPayload;
import com.example.ecommerceApplication.services.ProductDisplayService;



@RestController
@RequestMapping("/user/rest")
@EnableMethodSecurity
public class ProductDisplayController {

    @Autowired private ProductDisplayService productDisplayService;
    
    @GetMapping("/get-allProduct")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getAllProductDetails() {
        return ResponseEntity.ok(productDisplayService.getAllProduct());
    }

    @PostMapping("/save-cart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> saveCartDetails(@RequestBody CartPayload payload) {
        productDisplayService.saveCart(payload);
        return ResponseEntity.ok("Added to cart successfully");
    }

    @GetMapping("/get-cart/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getCartDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(productDisplayService.getCart(userId));
    }

    @DeleteMapping("/delete-cart/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteCartDetails(@PathVariable Long id) {
        productDisplayService.deleteCart(id);
        return ResponseEntity.ok("Removed successfully");
    }

    @PostMapping("/addRemove-cart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addRemoveCartDetails(@RequestBody CartPayload payload) {
        productDisplayService.addRemoveCart(payload);
        return ResponseEntity.ok("Success");
    }
    
    @GetMapping("/get-user/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(productDisplayService.getUser(id));
    }

    @PostMapping("/save-user")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserPayload payload) {
        productDisplayService.saveUser(payload);
        return ResponseEntity.ok("Saved successfully");
    }
}
