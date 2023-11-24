// Create InventoryController with GetMapping, PostMapping, PutMapping, and DeleteMapping
// for Inventory using InventoryService and Exceptions
// Path: src/main/java/com/example/demo/controller/InventoryController.java
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// Add Swagger Documentation for InventoryController

@RestController
@RequestMapping("/inventory")
/**
 * This class is responsible for managing the inventory.
 * It provides methods to retrieve, add, update, and delete inventory items.
 */
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // GetMapping to get all inventory items
    @GetMapping
    /**
     * Retrieves all inventory items.
     *
     * @return ResponseEntity<List<Inventory>> - The response entity containing the list of inventory items.
     */
    // Add Swagger Documentation
    @Operation(summary = "Get All Inventory", description = "Returns a list of all inventory items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryList);
    }

    // PostMapping to add an inventory item
    @PostMapping
    /**
     * Adds a new inventory item.
     *
     * @param inventory - The inventory item to be added.
     * @return ResponseEntity<Inventory> - The response entity containing the added inventory item.
     */
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory addedInventory = inventoryService.addInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInventory);
    }

    // PutMapping to update an inventory item
    @PutMapping("/{id}")
    /**
     * Updates an existing inventory item.
     *
     * @param id - The ID of the inventory item to be updated.
     * @param inventory - The updated inventory item.
     * @return ResponseEntity<Inventory> - The response entity containing the updated inventory item.
     */
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        if (!inventoryService.checkInventoryExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        return ResponseEntity.ok(updatedInventory);
    }

    // DeleteMapping to delete an inventory item
    @DeleteMapping("/{id}")
    /**
     * Deletes an existing inventory item.
     *
     * @param id - The ID of the inventory item to be deleted.
     * @return ResponseEntity<Boolean> - The response entity indicating whether the item was successfully deleted.
     */
    public ResponseEntity<Boolean> deleteInventory(@PathVariable Long id) {
        if (!inventoryService.checkInventoryExists(id)) {
            return ResponseEntity.notFound().build();
        }
        boolean deleted = inventoryService.deleteInventory(id);
        return ResponseEntity.ok(deleted);
    }
}