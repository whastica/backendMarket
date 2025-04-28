package com.whalensoft.backendMarket.web.controller;

import com.whalensoft.backendMarket.domain.Product;
import com.whalensoft.backendMarket.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron productos")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return products.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                ResponseEntity.ok(products);
    }

    @Operation(summary = "Obtener un producto por ID", description = "Devuelve un producto específico basado en el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable int productId) {
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary = "Obtener productos por categoría", description = "Devuelve una lista de productos que pertenecen a una categoría específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados en la categoría",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron productos para esta categoría")
    })
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "ID de la categoría", required = true)
            @PathVariable int categoryId) {
        List<Product> products = productService.getByCategory(categoryId).orElse(Collections.emptyList());
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Guardar un nuevo producto", description = "Guarda un nuevo producto en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto guardado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Error al guardar el producto")
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            Product savedProduct = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al guardar el producto: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un producto por ID", description = "Elimina un producto específico usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID del producto a eliminar", required = true)
            @PathVariable("productId") int productId) {
        if (!productService.deleteProduct(productId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
