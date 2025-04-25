package com.whalensoft.backendMarket.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_compra")
    private Integer idCompra;

    @Column(name="id_cliente")
    private String idCliente;

    private LocalDateTime fecha;

    @Column(name="medio_pago")
    private String medioPago;

    @ManyToOne()
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra" )
    private List<ComprasProducto> productos;

    private String comentario;
    private String estado;

}
