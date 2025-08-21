package com.example.ecommerceApplication.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerceApplication.models.Order;
import com.example.ecommerceApplication.pojos.OrderPayload;
import com.example.ecommerceApplication.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    public ResponseEntity<?> saveOrder(OrderPayload payload) {
        if(payload.getOrderId() == null) {
            Order order = new Order();
            order.setFirstName(payload.getFirstName());
            order.setLastName(payload.getLastName());
            order.setCity(payload.getCity());
            order.setAddress(payload.getAddress());
            order.setMobileNumber(payload.getMobileNumber());
            order.setEmail(payload.getEmail());
            order.setStatus(payload.getStatus());
            order.setDateOrdered(LocalDate.now());
            order.setOrderedAmount(payload.getOrderedAmount());
            order.setUserId(payload.getUserId());
            orderRepository.save(order);  
            return ResponseEntity.ok(Map.of("status","Placed order successfully", "orderId", order.getId()));
        }else {
            Order order = orderRepository.findById(payload.getOrderId()).get();
            order.setStatus(payload.getStatus());
            orderRepository.save(order);
            return ResponseEntity.ok(Map.of("status", "Updated successfully"));
        }
    }

    public ResponseEntity<?> getAllOrder(OrderPayload payload) {
        List<Order> orders = orderRepository.findAllByUserId(payload.getUserId());
        return ResponseEntity.ok(Map.of("orders", orders));
    }
    
}
