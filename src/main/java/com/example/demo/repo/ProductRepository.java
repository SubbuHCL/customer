package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//create a repository interface for the product with CrudRepository
import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
}



