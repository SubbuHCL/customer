package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invetorys")
@Getter
@Setter
@Data
public class Inventory {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private int quantity;
    
}
