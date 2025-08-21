package com.example.ecommerceApplication.services;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ecommerceApplication.models.Cart;
import com.example.ecommerceApplication.models.Product;
import com.example.ecommerceApplication.repositories.CartRepository;
import com.example.ecommerceApplication.repositories.ProductRepository;
import com.example.ecommerceApplication.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ProductDisplayServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductDisplayService productDisplayService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetAllProduct() {
        // Arrange
        Product mockUser1 = new Product(1L, "test", 123, "test", "test");
        Product mockUser2 = new Product(1L, "test", 123, "test", "test");
        List<Product> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(productRepository.findAll()).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = productDisplayService.getAllProduct();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Product> products = (List<Product>) body.get("products");
        assertEquals(2, products.size());
        assertEquals("test", products.get(0).getName());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetCart() {
        // Arrange
        Cart mockUser1 = new Cart(1L, "test", 123, 123, "test", 123L, 123L);
        Cart mockUser2 = new Cart(2L, "test", 123, 123, "test", 123L, 123L);
        List<Cart> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(cartRepository.findAllByUserId(123L)).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = productDisplayService.getCart(123L);
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Cart> carts = (List<Cart>) body.get("carts");
        assertEquals(2, carts.size());
        assertEquals("test", carts.get(0).getName());
        Mockito.verify(cartRepository, Mockito.times(1)).findAllByUserId(123L);
    }

    @Test
    public void testDeleteCart() {
        // Arrange
        Long cartId = 10L;

        // Act
        productDisplayService.deleteCart(cartId);

        // Assert
        verify(cartRepository, times(1)).deleteById(cartId);
    }

    
}
