package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Customer;

//Create a CustomerRepository interface that extends CrudRepository
//The CustomerRepository interface is an interface that extends CrudRepository, a type of interface defined in Spring Data JPA.
//CrudRepository has methods like save(), findAll(), findById(), deleteById(), etc.

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    
    
}
