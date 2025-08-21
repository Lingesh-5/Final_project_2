package com.example.ecommerceApplication.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerceApplication.models.Order;
import com.example.ecommerceApplication.models.Product;
import com.example.ecommerceApplication.pojos.ProductPayload;
import com.example.ecommerceApplication.repositories.OrderRepository;
import com.example.ecommerceApplication.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private OrderRepository orderRepository;

    public void saveProduct(ProductPayload payload) {
        if(payload.getId() == null) {
            Product product = new Product();
            product.setName(payload.getName());
            product.setPrice(payload.getPrice());
            product.setDescription(payload.getDescription());
            product.setBrand(payload.getBrand());
            productRepository.save(product);
        }else {
            Product product = productRepository.findById(payload.getId()).get();
            product.setName(payload.getName());
            product.setPrice(payload.getPrice());
            product.setDescription(payload.getDescription());
            product.setBrand(payload.getBrand());
            productRepository.save(product);
        }
    }

    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(Map.of("products", products));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public ResponseEntity<?> getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return ResponseEntity.ok(Map.of("product", product));
    }

    public ResponseEntity<?> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(Map.of("orders", orders));
    }
}
