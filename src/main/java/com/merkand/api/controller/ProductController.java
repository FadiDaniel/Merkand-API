package com.merkand.api.controller;

import com.merkand.api.dto.ProductDto;
import com.merkand.api.entity.Product;
import com.merkand.api.service.implementation.ProductServImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductServImpl service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        try {
            List<ProductDto> productsDto = service.getAll()
                    .stream()
                    .map(product -> mapper.map(product, ProductDto.class))
                    .toList();
            System.out.println(productsDto);
            return ResponseEntity.ok(productsDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la lista de productos: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto){
        try {
            var product = mapper.map(productDto, Product.class);
            service.save(product);
            var createdProductDto = mapper.map(product, ProductDto.class);
            return ResponseEntity.status(201).body(createdProductDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear el producto: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al procesar el producto");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        try {
            Product existingProduct = service.get(id);
            if (existingProduct == null) {
                return ResponseEntity.status(404).body("Producto con ID " + id + " no encontrado");
            }
            mapper.map(productDto, existingProduct);
            existingProduct.setId(id);
            service.save(existingProduct);
            return ResponseEntity.ok(mapper.map(existingProduct, ProductDto.class));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el producto: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al actualizar el producto");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateProduct(@PathVariable Long id) {
        try {
            Product existingProduct = service.get(id);
            if (existingProduct == null) {
                return ResponseEntity.status(404).body("Producto con ID " + id + " no encontrado");
            }
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el producto: " + e.getMessage());
        }
    }
}