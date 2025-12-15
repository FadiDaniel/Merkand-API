package com.merkand.api.controller;

import com.merkand.api.dto.ProductDto;
import com.merkand.api.service.implementation.ProductServImpl;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productsDto = service.getAll()
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .toList();
        System.out.println(productsDto);
        return ResponseEntity.ok(productsDto);
    }

}
