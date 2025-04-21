package com.whalensoft.backendMarket.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
