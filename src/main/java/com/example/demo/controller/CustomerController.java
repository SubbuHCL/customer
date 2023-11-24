package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController("/customer")
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Returns a list of all customers.
     *
     * @return a list of all customers
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Returns a customer with the given ID.
     *
     * @param id the ID of the customer to retrieve
     * @return a ResponseEntity with the retrieved customer and an HTTP status of
     *         200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        System.out.println(id);
        
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Adds a new customer.
     *
     * @param customer the customer to add
     * @return a ResponseEntity with the added customer and an HTTP status of 200
     *         (OK)
     */
    @PostMapping

    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer)  { // Customer
        try{
            customer = customerService.addCustomer(customer);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates an existing customer.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer information
     * @return a ResponseEntity with the updated customer and an HTTP status of 200
     *         (OK)
     */
    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customerToUpdate = customerService.updateCustomer(id, customer);
        if (customerToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Deletes a customer with the given ID.
     *
     * @param id the ID of the customer to delete
     * @return a ResponseEntity with no content and an HTTP status of 200 (OK)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) throws Exception {
        try {
            boolean isNotFound = customerService.deleteCustomer(id);
            if (isNotFound) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
