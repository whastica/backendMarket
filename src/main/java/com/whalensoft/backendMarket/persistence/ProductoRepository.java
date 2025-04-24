package com.whalensoft.backendMarket.persistence;

import com.whalensoft.backendMarket.persistence.crud.ProductoCrudRepository;
import com.whalensoft.backendMarket.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {return (List<Producto>) productoCrudRepository.findAll();}

    public List<Producto> getByCategroria(int idCategoria){
        return productoCrudRepository.findByIdCategoria(idCategoria);
    }

    public Optional<List<Producto>> getProductoEscaso(int cantidad){
        return  productoCrudRepository.findByIdCantidadStocklessThanAndEstado(cantidad, true);
    }

    public Optional <Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    public void Delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}

