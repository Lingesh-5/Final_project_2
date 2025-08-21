package com.example.ecommerceApplication.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ecommerceApplication.services.ProductDisplayService;

@WebMvcTest(ProductDisplayController.class)
@AutoConfigureMockMvc(addFilters=false)
public class ProductDisplayControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ProductDisplayService productDisplayService;

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testGetAllProductDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-allProduct")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testSaveCartDetails() throws Exception {
        String requestBody = """
                        {
                            "name":"test",
                            "price":123,
                            "quantity":123,
                            "brand":"test",
                            "userId":123,
                            "productId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/save-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testGetCartDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-cart/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testDeleteCartDetails() throws Exception {
    
        mockMvc.perform(delete("/user/rest/delete-cart/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testAddRemoveCartDetails() throws Exception {
        String requestBody = """
                        {
                            "cartId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/addRemove-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testGetUserDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-user/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"CUSTOMER"})
    public void testSaveUserDetails() throws Exception {
        String requestBody = """
                        {
                            "name":"test",
                            "email":"test@mail.com",
                            "userId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/save-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }
    
}
