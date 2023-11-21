//Generate InventoryController for GetMapping, PostMapping, PutMapping, DeleteMapping with Inventory using InventoryService and ExceptionHandling
package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;

import io.swagger.annotations.ApiResponses;

/**
 * This class represents the Inventory Controller which handles HTTP requests related to inventory.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * This method handles the HTTP GET request to retrieve all inventory.
     * @return ResponseEntity<List<Inventory>> Returns a ResponseEntity containing a list of Inventory objects.
     */
    @ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success"),
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })        
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryList);
    }

    /**
     * This method handles the HTTP POST request to add inventory.
     * @param inventory The Inventory object to be added.
     * @return ResponseEntity<Inventory> Returns a ResponseEntity containing the added Inventory object.
     */
    //Swagger Documentation
    @ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created"),
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory addedInventory = inventoryService.addInventory(inventory);
        String username = "admin";
        String password = "admin";
        System.out.println("Username: " + username + " Password: " + password);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInventory);
    }

    /**
     * This method handles the HTTP PUT request to update inventory.
     * @param id The id of the Inventory object to be updated.
     * @param updatedInventory The updated Inventory object.
     * @return ResponseEntity<Inventory> Returns a ResponseEntity containing the updated Inventory object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory updatedInventory) {
        Inventory inventory = inventoryService.updateInventory(id, updatedInventory);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method handles the HTTP DELETE request to delete inventory.
     * @param id The id of the Inventory object to be deleted.
     * @return ResponseEntity<Boolean> Returns a ResponseEntity containing a boolean value indicating whether the Inventory object was deleted successfully or not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteInventory(@PathVariable Long id) {
        boolean deleted = inventoryService.deleteInventory(id);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method handles exceptions thrown by the controller.
     * @param e The Exception object thrown.
     * @return ResponseEntity<String> Returns a ResponseEntity containing the error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}


