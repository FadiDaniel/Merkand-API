package com.merkand.api.controller;

import com.merkand.api.dto.ProductDto;
import com.merkand.api.entity.Product;
import com.merkand.api.exception.ResourceNotFoundException;
import com.merkand.api.mapper.ProductMapper;
import com.merkand.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:4200")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productsDto = service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(productsDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        var product = mapper.toEntity(productDto);
        service.save(product);
        return ResponseEntity.status(201).body(mapper.toDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Product existingProduct = service.get(id);
        if (existingProduct == null) {
            throw new ResourceNotFoundException("Producto con ID " + id + " no encontrado");
        }
        mapper.updateEntityFromDto(productDto, existingProduct);
        existingProduct.setId(id);
        service.save(existingProduct);
        return ResponseEntity.ok(mapper.toDto(existingProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id) {
        Product existingProduct = service.get(id);
        if (existingProduct == null) {
            throw new ResourceNotFoundException("Producto con ID " + id + " no encontrado");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}