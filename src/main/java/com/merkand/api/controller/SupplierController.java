package com.merkand.api.controller;

import com.merkand.api.dto.SupplierDto;
import com.merkand.api.service.implementation.SupplierServImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<SupplierDto>> getAllSuppliers(){
        List<SupplierDto> suppliersDto = service.getAll()
                .stream()
                .map(supplier -> mapper.map(supplier, SupplierDto.class))
                .toList();
        System.out.println(suppliersDto);
        return ResponseEntity.ok(suppliersDto);
    }
}
