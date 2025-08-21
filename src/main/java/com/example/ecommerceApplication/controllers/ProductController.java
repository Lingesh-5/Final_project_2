package com.example.ecommerceApplication.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceApplication.pojos.ProductPayload;
import com.example.ecommerceApplication.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/rest")
@EnableMethodSecurity
@Validated
public class ProductController {

    @Autowired private ProductService productService;

    @PostMapping("/save-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveProductDetails(@Valid @RequestBody ProductPayload payload) {
        productService.saveProduct(payload);
        return ResponseEntity.ok(Map.of("status", "Saved successfully"));
    }

    @GetMapping("/get-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProductDetails() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping("/delete-product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductDetails(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted successfully");

    }

    @GetMapping("/get-productById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/order/get-allOrder")
    public ResponseEntity<?> getAllOrderDetails() {
        return ResponseEntity.ok(productService.getAllOrder());
    }
    
    

    
    
}
