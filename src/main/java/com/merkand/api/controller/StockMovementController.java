package com.merkand.api.controller;

import com.merkand.api.dto.StockMovementDto;
import com.merkand.api.entity.Product;
import com.merkand.api.entity.StockMovement;
import com.merkand.api.service.ProductService;
import com.merkand.api.service.StockMovementService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movements")
@CrossOrigin(origins="http://localhost:4200")
public class StockMovementController {
    @Autowired
    public StockMovementService service;
    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<?> getAllMovements(){
        try {
        List<StockMovementDto> movements = service.getAll()
                .stream()
                .map(movement -> mapper.map(movement, StockMovementDto.class))
                .toList();
        System.out.println(movements);
        return ResponseEntity.ok(movements);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la lista de movimientos: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createMovement(@Valid @RequestBody StockMovementDto movementDto){
        try {
            StockMovement movement = mapper.map(movementDto, StockMovement.class);
            movement.setDate(LocalDateTime.now());
            service.save(movement);
            var createdMovementDto = mapper.map(movement, StockMovementDto.class);
            return ResponseEntity.status(201).body(createdMovementDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al registrar el movimiento: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al procesar el movimiento de stock\n"+e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovement(@PathVariable Long id, @Valid @RequestBody StockMovementDto movementDto) {
        try {
            StockMovement existingMovement = service.get(id);
            if (existingMovement == null) {
                return ResponseEntity.status(404).body("Movimiento con ID " + id + " no encontrado");
            }
            mapper.map(movementDto, existingMovement);
            existingMovement.setId(id);
            service.save(existingMovement);
            return ResponseEntity.ok(mapper.map(existingMovement, StockMovementDto.class));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el movimiento: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al actualizar el movimiento");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovement(@PathVariable Long id) {
            return ResponseEntity.status(400).body("Eliminación de movimientos no permitida.");
    }
}