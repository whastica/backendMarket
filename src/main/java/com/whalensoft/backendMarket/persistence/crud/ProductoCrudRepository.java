package com.whalensoft.backendMarket.persistence.crud;

import com.whalensoft.backendMarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    //Query metods nos ayudan a recuperar informacion que con los repositorios no tenemos acceso
    List<Producto> findByIdCategoria(int idCategoria);


    Optional <List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
