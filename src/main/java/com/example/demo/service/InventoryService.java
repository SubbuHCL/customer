package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Inventory;
// import InventoryRepository
import com.example.demo.repo.InventoryRepository;


//Generate Documentation
/**
 * This class is responsible for managing the inventory.
 * It provides methods to retrieve, add, update, and delete inventory items.
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Returns all Inventory as List
     * @return List of Inventory
     */
    public List<Inventory> getAllInventory() {
        Iterable<Inventory> iterable = inventoryRepository.findAll();
        List<Inventory> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    /**
     * Returns a Inventory by id
     * @param id The id of the Inventory to retrieve
     * @return The Inventory with the specified id, or null if not found
     */
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    /**
     * Adds a Inventory to the database
     * @param inventory The Inventory to add
     * @return The added Inventory
     */
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * Updates a Inventory based on id
     * @param id The id of the Inventory to update
     * @param updatedInventory The updated Inventory object
     * @return The updated Inventory, or null if the Inventory with the specified id was not found
     */
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        Inventory inventoryToUpdate = inventoryRepository.findById(id).orElse(null);
        if (inventoryToUpdate != null) {
            inventoryToUpdate.setName(updatedInventory.getName());
            inventoryToUpdate.setQuantity(updatedInventory.getQuantity());
            inventoryToUpdate.setPrice(updatedInventory.getPrice());
            return inventoryRepository.save(inventoryToUpdate);
        }
        return null;
    }

    /**
     * Deletes a Inventory based on id if it exists
     * @param id The id of the Inventory to delete
     * @return true if the Inventory was deleted, false if the Inventory with the specified id was not found
     */
    public boolean deleteInventory(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
