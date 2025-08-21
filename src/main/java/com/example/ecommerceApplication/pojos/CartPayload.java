package com.example.ecommerceApplication.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartPayload {
    private String name;
    private Integer price;
    private Integer quantity;
    private String brand;
    private Long userId;
    private Long productId;
    private Long cartId;
    private String task;
}
