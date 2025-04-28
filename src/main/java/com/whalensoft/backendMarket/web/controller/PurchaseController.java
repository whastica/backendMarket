package com.whalensoft.backendMarket.web.controller;

import com.whalensoft.backendMarket.domain.Purchase;
import com.whalensoft.backendMarket.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Operations related to customer purchases") // <-- Agrupa todo en Swagger
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Operation(summary = "Get all purchases", description = "Retrieve a list of all purchases in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get purchases by client ID", description = "Retrieve a list of purchases for a specific client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved purchases"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "ID of the client whose purchases you want to retrieve", required = true)
            @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Save a new purchase", description = "Create and save a new purchase record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(
            @Parameter(description = "Purchase object to be saved", required = true)
            @RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}