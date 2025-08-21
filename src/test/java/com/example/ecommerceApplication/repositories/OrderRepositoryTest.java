package com.example.ecommerceApplication.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ecommerceApplication.models.Order;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired private OrderRepository orderRepository;

    @Test
    public void testFindAllByUserId() {
        // Arrange
        orderRepository.save(new Order(null, "test1", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 1L));
        orderRepository.save(new Order(null, "test2", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 1L));

        // Act
        List<Order> result = orderRepository.findAllByUserId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
}
