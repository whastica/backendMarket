package com.whalensoft.backendMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMarketApplication.class, args);
	}
		//Estructura enfocada en dominio
		//Dominio cuenta con DTOs y objetos del dominio, servicios, repositorios
		//Tendremos una capa web para los controladores y una capa de persistencia para la base de datos
}
