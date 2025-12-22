package com.merkand.api.controller;

import com.merkand.api.dto.SupplierDto;
import com.merkand.api.entity.Supplier;
import com.merkand.api.service.implementation.SupplierServImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins="http://localhost:4200")
public class SupplierController {
    @Autowired
    private SupplierServImpl service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllSuppliers(){
        try {
            List<SupplierDto> suppliersDto = service.getAll()
                    .stream()
                    .map(supplier -> mapper.map(supplier, SupplierDto.class))
                    .toList();
            System.out.println(suppliersDto);
            return ResponseEntity.ok(suppliersDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la lista de proveedores: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierDto supplierDto){
        try {
            var supplier = mapper.map(supplierDto, Supplier.class);
            service.save(supplier);
            var createdSupplierDto = mapper.map(supplier, SupplierDto.class);
            return ResponseEntity.status(201).body(createdSupplierDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear el proveedor: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Error interno al procesar el proveedor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDto supplierDto) {
        try {
            Supplier existingSupplier = service.get(id);
            if (existingSupplier == null) {
                return ResponseEntity.status(404).body("Proveedor con ID " + id + " no encontrado");
            }
            mapper.map(supplierDto, existingSupplier);
            existingSupplier.setId(id);
            service.save(existingSupplier);
            return ResponseEntity.ok(mapper.map(existingSupplier, SupplierDto.class));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el proveedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al actualizar el proveedor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateSupplier(@PathVariable Long id) {
        try {
            Supplier existingSupplier = service.get(id);
            if (existingSupplier == null) {
                return ResponseEntity.status(404).body("Proveedor con ID " + id + " no encontrado");
            }
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}