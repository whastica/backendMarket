package com.whalensoft.backendMarket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    private Integer categoryId;
    private String category;
    private Boolean active;
}
