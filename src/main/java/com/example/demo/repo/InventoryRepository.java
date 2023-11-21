package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    
}
