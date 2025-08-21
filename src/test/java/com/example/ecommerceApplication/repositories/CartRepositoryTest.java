package com.example.ecommerceApplication.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ecommerceApplication.models.Cart;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest {

    @Autowired private CartRepository cartRepository;

    @Test
    public void testFindByProductIdAndUserId() {
        // Arrange
        Cart cart = new Cart(null, "test", 123, 1, "test", 123L, 1234L);
        cartRepository.save(cart);

        // Act
        Cart result = cartRepository.findByProductIdAndUserId(1234L, 123L);

        // Assert
        assertNotNull(result);
        assertEquals("test", result.getName());
    }

    @Test
    public void testFindAllByUserId() {
        // Arrange
        cartRepository.save(new Cart(null, "test1", 123, 1, "test1", 1234L, 1234L));
        cartRepository.save(new Cart(null, "test2", 123, 1, "test2", 1234L, 1234L));

        // Act
        List<Cart> result = cartRepository.findAllByUserId(1234L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
}
