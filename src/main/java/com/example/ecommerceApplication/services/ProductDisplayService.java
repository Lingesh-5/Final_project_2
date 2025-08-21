package com.example.ecommerceApplication.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerceApplication.models.Cart;
import com.example.ecommerceApplication.models.Product;
import com.example.ecommerceApplication.models.User;
import com.example.ecommerceApplication.pojos.CartPayload;
import com.example.ecommerceApplication.pojos.UserPayload;
import com.example.ecommerceApplication.repositories.CartRepository;
import com.example.ecommerceApplication.repositories.ProductRepository;
import com.example.ecommerceApplication.repositories.UserRepository;

@Service
public class ProductDisplayService {

    @Autowired private ProductRepository productRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private UserRepository userRepository;

    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(Map.of("products", products));
    }

    public void saveCart(CartPayload payload) {
        Product product = productRepository.findById(payload.getProductId()).get();

        Cart cart = cartRepository.findByProductIdAndUserId(payload.getProductId(), payload.getUserId());

        if(cart == null) {
            Cart newCart = new Cart();
            newCart.setName(product.getName());
            newCart.setPrice(product.getPrice());
            newCart.setQuantity(payload.getQuantity());
            newCart.setBrand(product.getBrand());
            newCart.setUserId(payload.getUserId());
            newCart.setProductId(payload.getProductId());
            cartRepository.save(newCart);
        }else {
            Integer totalQuantity = cart.getQuantity()+payload.getQuantity();
            cart.setQuantity(totalQuantity);
            cartRepository.save(cart);
        }
    }

    public ResponseEntity<?> getCart(Long userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        return ResponseEntity.ok(Map.of("carts", carts));
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void addRemoveCart(CartPayload payload) {
        Cart cart = cartRepository.findById(payload.getCartId()).get();

        if(payload.getTask().equals("Add")) {
            Integer totalQuantity = cart.getQuantity() + payload.getQuantity();
            cart.setQuantity(totalQuantity);
            cartRepository.save(cart);
        }else if(payload.getTask().equals("Remove")) {
            Integer totalQuantity = cart.getQuantity() - payload.getQuantity();
            cart.setQuantity(totalQuantity);
            cartRepository.save(cart);
        }
    }

    public ResponseEntity<?> getUser(Long id) {
        User user = userRepository.findById(id).get();
        return ResponseEntity.ok(Map.of("user", user));
    }

    public void saveUser(UserPayload payload) {
        User user = userRepository.findById(payload.getUserId()).get();
        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        userRepository.save(user);
    }
    
}
