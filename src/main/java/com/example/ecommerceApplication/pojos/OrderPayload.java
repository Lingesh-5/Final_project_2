package com.example.ecommerceApplication.pojos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload {
    @NotBlank(message="Please enter first name")
    private String firstName;
    @NotBlank(message="Please enter last name")
    private String lastName;
    @NotBlank(message="Please enter city")
    private String city;
    @NotBlank(message="Please enter address")
    private String address;
    @NotBlank(message="Please enter mobile number")
    private String mobileNumber;
    @NotBlank(message="Please enter email")
    private String email; 
    private String status;
    private Integer orderedAmount;
    private Long userId;
    private Long orderId;
}
