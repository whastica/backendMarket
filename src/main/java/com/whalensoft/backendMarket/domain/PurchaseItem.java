package com.whalensoft.backendMarket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseItem {
    private int productId;
    private int quantity;
    private double total;
    private boolean active;
}
