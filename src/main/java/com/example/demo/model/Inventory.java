package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@Data
@NoArgsConstructor
public class Inventory {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private int quantity;
    
}
