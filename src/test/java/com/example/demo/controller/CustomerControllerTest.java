package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Test
    public void testGetCustomerById() throws Exception {
        // Arrange
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.getCustomer(customerId)).thenReturn(customer);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId));
    }

    // Add more test methods for other controller methods

    @Test
    public void testAddCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        // Add more properties to the customer object as needed

        when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(customer);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customer.getId()))
                .andExpect(jsonPath("$.name").value(customer.getName()));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customers.add(customer);

        // Add more customers as needed

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(customers.size()));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Arrange
        long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Updated Name");
        // Set other properties as needed

        when(customerService.updateCustomer(Mockito.eq(customerId), Mockito.any(Customer.class)))
                .thenReturn(updatedCustomer);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(put("/customer/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.name").value(updatedCustomer.getName()));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // Arrange
        long customerId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(delete("/customer/{id}", customerId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerById_CustomerNotFound() throws Exception {
        // Arrange
        long customerId = 1L;

        when(customerService.getCustomer(customerId)).thenReturn(null);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddCustomer_InvalidRequestBody() throws Exception {
        // Arrange
        String invalidRequestBody = "{\"invalid\": \"request\"}";

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCustomer_CustomerNotFound() throws Exception {
        // Arrange
        long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Updated Name");
        // Set other properties as needed

        when(customerService.updateCustomer(Mockito.eq(customerId), Mockito.any(Customer.class))).thenReturn(null);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(put("/customer/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedCustomer)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCustomer_CustomerNotFound() throws Exception {
        // Arrange
        long customerId = 1L;

        doThrow(new RuntimeException("Customer not found")).when(customerService).deleteCustomer(customerId);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Act & Assert
        mockMvc.perform(delete("/customer/{id}", customerId))
                .andExpect(status().isNotFound());
    }

}