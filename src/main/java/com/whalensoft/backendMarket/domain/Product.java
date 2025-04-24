package com.whalensoft.backendMarket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int productid;
    private String name;
    private double price;
    private int stock;
    private boolean active;
    private Category category;
}
