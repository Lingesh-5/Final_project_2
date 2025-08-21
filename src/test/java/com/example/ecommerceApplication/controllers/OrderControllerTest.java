package com.example.ecommerceApplication.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ecommerceApplication.services.OrderService;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters=false)
public class OrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private OrderService orderService;

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testSaveOrderDetails() throws Exception {
        String requestBody = """
                        {
                            "firstName":"test",
                            "lastName":"test",
                            "city":"test",
                            "address":"test",
                            "mobileNumber":"test",
                            "email":"test",
                            "status":"test",
                            "orderedAmount":123,
                            "userId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/save-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testGetAllOrderDetails() throws Exception {
        String requestBody = """
                        {
                            "userId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/get-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }
    
}
