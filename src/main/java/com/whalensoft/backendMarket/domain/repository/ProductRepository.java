package com.whalensoft.backendMarket.domain.repository;

import com.whalensoft.backendMarket.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int idCategory);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional <Product> getProdcut(int productId);
    Product save(Product product);
    void delete(Product product);
}
