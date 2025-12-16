package com.merkand.api.controller;

import com.merkand.api.dto.OrderDto;
import com.merkand.api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> ordersDto = service.getAll()
                .stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .toList();
        System.out.println(ordersDto);
        return ResponseEntity.ok(ordersDto);
    }
}
