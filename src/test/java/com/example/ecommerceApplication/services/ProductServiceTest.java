package com.example.ecommerceApplication.services;

import java.time.LocalDate;
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

import com.example.ecommerceApplication.models.Order;
import com.example.ecommerceApplication.models.Product;
import com.example.ecommerceApplication.repositories.OrderRepository;
import com.example.ecommerceApplication.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testGetAllProduct() {
        // Arrange
        Product mockUser1 = new Product(1L, "test", 123, "test", "test");
        Product mockUser2 = new Product(1L, "test", 123, "test", "test");
        List<Product> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(productRepository.findAll()).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = productService.getAllProduct();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Product> products = (List<Product>) body.get("products");
        assertEquals(2, products.size());
        assertEquals("test", products.get(0).getName());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long id = 10L;

        // Act
        productService.deleteProduct(id);

        // Assert
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllOrder() {
        // Arrange
        Order mockUser1 = new Order(1L, "test", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 123L);
        Order mockUser2 = new Order(1L, "test", "test", "test", "test", "test", "test", "test", LocalDate.now(), 123, 123L);
        List<Order> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(orderRepository.findAll()).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = productService.getAllOrder();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Order> orders = (List<Order>) body.get("orders");
        assertEquals(2, orders.size());
        assertEquals("test", orders.get(0).getFirstName());
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }
    
}
