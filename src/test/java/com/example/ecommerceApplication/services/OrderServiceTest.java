package com.example.ecommerceApplication.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ecommerceApplication.models.Order;
import com.example.ecommerceApplication.pojos.OrderPayload;
import com.example.ecommerceApplication.repositories.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void testSaveOrder() {
        // Arrange
        OrderPayload payload = new OrderPayload();
        payload.setOrderId(200L);
        payload.setStatus("CREATED");

        Order existingOrder = new Order();
        existingOrder.setId(200L);
        existingOrder.setStatus("PAID");

        when(orderRepository.findById(200L)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(existingOrder);

        // Act
        ResponseEntity<?> response = orderService.saveOrder(payload);

        // Assert
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        assertNotNull(body);
        assertEquals("Updated successfully", body.get("status"));

        verify(orderRepository, times(1)).findById(200L);
        verify(orderRepository, times(1)).save(existingOrder);
    }

    @Test
    public void testGetAllOrder() {
        // Arrange
        OrderPayload payload = new OrderPayload();
        payload.setUserId(123L);
        Order mockUser1 = new Order(1L, "test", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 123L);
        Order mockUser2 = new Order(1L, "test", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 123L);
        List<Order> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(orderRepository.findAllByUserId(123L)).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = orderService.getAllOrder(payload);
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Order> orders = (List<Order>) body.get("orders");
        assertEquals(2, orders.size());
        assertEquals("test", orders.get(0).getFirstName());
        Mockito.verify(orderRepository, Mockito.times(1)).findAllByUserId(123L);
    }

}
