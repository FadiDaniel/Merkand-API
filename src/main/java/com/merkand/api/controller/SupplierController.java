package com.merkand.api.controller;

import com.merkand.api.dto.SupplierDto;
import com.merkand.api.entity.Supplier;
import com.merkand.api.exception.ResourceNotFoundException;
import com.merkand.api.mapper.SupplierMapper;
import com.merkand.api.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins="http://localhost:4200")
public class SupplierController {
    private final SupplierService service;
    private final SupplierMapper mapper;

    public SupplierController(SupplierService service, SupplierMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers(){
        List<SupplierDto> suppliersDto = service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(suppliersDto);
    }

    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@Valid @RequestBody SupplierDto supplierDto){
        var supplier = mapper.toEntity(supplierDto);
        service.save(supplier);
        return ResponseEntity.status(201).body(mapper.toDto(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDto supplierDto) {
        Supplier existingSupplier = service.get(id);
        if (existingSupplier == null) {
            throw new ResourceNotFoundException("Proveedor con ID " + id + " no encontrado");
        }
        mapper.updateEntityFromDto(supplierDto, existingSupplier);
        existingSupplier.setId(id);
        service.save(existingSupplier);
        return ResponseEntity.ok(mapper.toDto(existingSupplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateSupplier(@PathVariable Long id) {
        Supplier existingSupplier = service.get(id);
        if (existingSupplier == null) {
            throw new ResourceNotFoundException("Proveedor con ID " + id + " no encontrado");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}