package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //create a method that returns a list of all customers
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    //create a method that returns a customer by id
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).get();
    }

    //create a method that adds a customer
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    
    //create a method that updates a customer
    public Customer updateCustomer(Long id, Customer customer){
        Customer customerToUpdate = customerRepository.findById(id).get();
        if(customerToUpdate == null){
            return null;
        }
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        return customerRepository.save(customerToUpdate);
    }

    //create a method that deletes a customer
    public boolean deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).get();
        if(customer == null){
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }
}
