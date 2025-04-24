package com.whalensoft.backendMarket.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    private String id;

    private String nombre;

    @Column(name="apellidos")
    private String apellido;

    private Integer celular;
    private String direccion;

    @Column(name="correo_electronico")
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
