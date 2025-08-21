package com.example.ecommerceApplication.pojos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {
    @NotBlank(message="Please enter first name")
    private String name;
    private Integer price;
    @NotBlank(message="Please enter first description")
    private String description;
    @NotBlank(message="Please enter first brand")
    private String brand;
    private Long id;
}
