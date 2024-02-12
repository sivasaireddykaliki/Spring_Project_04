package com.example.springproject_04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private String description;
    private String manufacturer;
    private String availability;
}
