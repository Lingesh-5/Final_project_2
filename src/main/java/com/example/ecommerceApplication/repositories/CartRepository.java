package com.example.ecommerceApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerceApplication.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProductIdAndUserId(Long productId, Long userId);
    List<Cart> findAllByUserId(Long userId);
}
