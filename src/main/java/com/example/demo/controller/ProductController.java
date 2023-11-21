package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//Create a  RestController  with GetMapping, PostMapping, PutMapping, and DeleteMapping with ProductService
import org.springframework.web.bind.annotation.RestController;

//import all necessary statements
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController("/api/products")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // create a GetMapping to get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //create a PostMapping to add a product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    //create a PutMapping to update a product
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product) {
        if (!productService.checkProductExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    //create a DeleteMapping to delete a product
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        if (!productService.checkProductExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
