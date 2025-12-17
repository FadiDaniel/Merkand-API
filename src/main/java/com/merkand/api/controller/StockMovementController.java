package com.merkand.api.controller;

import com.merkand.api.dto.StockMovementDto;
import com.merkand.api.entity.StockMovement;
import com.merkand.api.service.StockMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<StockMovementDto>> getAllMovements(){
        List<StockMovementDto> movements = service.getAll()
                .stream()
                .map(movement -> mapper.map(movement, StockMovementDto.class))
                .toList();
        System.out.println(movements);
        return ResponseEntity.ok(movements);
    }
}
