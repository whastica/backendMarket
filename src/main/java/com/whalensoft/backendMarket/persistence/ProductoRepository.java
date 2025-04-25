package com.whalensoft.backendMarket.persistence;

import com.whalensoft.backendMarket.domain.Product;
import com.whalensoft.backendMarket.domain.repository.ProductRepository;
import com.whalensoft.backendMarket.persistence.crud.ProductoCrudRepository;
import com.whalensoft.backendMarket.persistence.entity.Producto;
import com.whalensoft.backendMarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;


    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int idCategory) {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findByIdCategoria(idCategory);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional <List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        //return productos.map(prods->mapper.toProducts(prods))
        return productos.map(mapper::toProducts);
    }

    @Override
    public Optional getProdcut(int productId){
        return productoCrudRepository.findById(productId).map(mapper::toProduct);
    }

    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}

