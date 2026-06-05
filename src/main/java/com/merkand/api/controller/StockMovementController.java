package com.merkand.api.controller;

import com.merkand.api.dto.StockMovementDto;
import com.merkand.api.entity.StockMovement;
import com.merkand.api.exception.BusinessException;
import com.merkand.api.exception.ResourceNotFoundException;
import com.merkand.api.mapper.StockMovementMapper;
import com.merkand.api.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movements")
@CrossOrigin(origins="http://localhost:4200")
public class StockMovementController {
    private final StockMovementService service;
    private final StockMovementMapper mapper;

    public StockMovementController(StockMovementService service, StockMovementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<List<StockMovementDto>> getAllMovements(){
        List<StockMovementDto> movements = service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<StockMovementDto> createMovement(@Valid @RequestBody StockMovementDto movementDto){
        StockMovement movement = mapper.toEntity(movementDto);
        movement.setDate(LocalDateTime.now());
        service.save(movement);
        return ResponseEntity.status(201).body(mapper.toDto(movement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovementDto> updateMovement(@PathVariable Long id, @Valid @RequestBody StockMovementDto movementDto) {
        StockMovement existingMovement = service.get(id);
        if (existingMovement == null) {
            throw new ResourceNotFoundException("Movimiento con ID " + id + " no encontrado");
        }
        mapper.updateEntityFromDto(movementDto, existingMovement);
        existingMovement.setId(id);
        service.save(existingMovement);
        return ResponseEntity.ok(mapper.toDto(existingMovement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        if (!service.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento con ID " + id + " no encontrado");
        }
        throw new BusinessException("Eliminación de movimientos no permitida");
    }
}