package com.example.demo.service;

// import All necessary statements
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//create a Product Service with Product Repository for all CRUD operations
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //create a method that returns a list of all products
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    //create a method that returns a product by id
    public Product getProduct(Long id){
        return productRepository.findById(id).get();
    }

    //create a method that adds a product
    // create a method that adds a product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    //create a method that updates a product
    // create a method that updates a product
    public Product updateProduct(Product product) {
        // Check if the product exists
        if (!checkProductExists(product.getId())) {
            return null;
        }
        return productRepository.save(product);
    }

    //check if the product exists
    // Check if the product exists
    public boolean checkProductExists(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent();
    }

    //create a method that deletes a product
    // create a method that deletes a product
    public boolean deleteProduct(Long id) {
        // Check if the product exists
        boolean exists = checkProductExists(id);
        if (!exists) {
            return false;  
        }
        productRepository.deleteById(id);
        return true;
    }
    
}
