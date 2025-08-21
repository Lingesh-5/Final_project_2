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

import com.example.ecommerceApplication.services.ProductService;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters=false)
public class ProductControllerTest {
    
    @Autowired private MockMvc mockMvc;
    @MockBean private ProductService productService;

    @Test
    @WithMockUser(username="testuser", roles={"ADMIN"})
    public void testSaveProductDetails() throws Exception {
        String requestBody = """
                        {
                            "name":"test",
                            "price":123,
                            "description":"test",
                            "brand":"test",
                            "id":123
                        }         
                """;
        mockMvc.perform(
            post("/admin/rest/save-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"ADMIN"})
    public void testGetAllProductDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-product")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"ADMIN"})
    public void testDeleteProductDetails() throws Exception {
    
        mockMvc.perform(delete("/admin/rest/delete-product/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"ADMIN"})
    public void testGetProductDetailsById() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-productById/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", roles={"ADMIN"})
    public void testGetAllOrderDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/order/get-allOrder")).andExpect(status().isOk());
    }
}
