package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Data
public class Customer {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String address;

}
