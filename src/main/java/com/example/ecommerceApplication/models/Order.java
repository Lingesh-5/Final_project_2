package com.example.ecommerceApplication.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String address;
    @Column(nullable=false)
    private String mobileNumber;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String status;
    @Column(nullable=false)
    private LocalDate dateOrdered;
    @Column(nullable=false)
    private Integer orderedAmount;
    @Column(nullable=false)
    private Long userId;
    
}
