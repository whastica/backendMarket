package com.whalensoft.backendMarket.persistence;

import com.whalensoft.backendMarket.domain.Purchase;
import com.whalensoft.backendMarket.domain.repository.PurchaseRepository;
import com.whalensoft.backendMarket.persistence.crud.CompraCrudRepository;
import com.whalensoft.backendMarket.persistence.entity.Compra;
import com.whalensoft.backendMarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        // Convertir el DTO o modelo de dominio a la entidad de persistencia
        Compra compra = purchaseMapper.toCompra(purchase);

        // Asegurarse de establecer la relación bidireccional para JPA
        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }

        // Guardar la entidad y mapearla de vuelta al modelo de dominio
        Compra savedCompra = compraCrudRepository.save(compra);

        return purchaseMapper.toPurchase(savedCompra);
    }
}
