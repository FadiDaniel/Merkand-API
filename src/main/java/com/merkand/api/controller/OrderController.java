package com.merkand.api.controller;

import com.merkand.api.dto.OrderDto;
import com.merkand.api.entity.Order;
import com.merkand.api.exception.ResourceNotFoundException;
import com.merkand.api.mapper.OrderMapper;
import com.merkand.api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> ordersDto = service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(ordersDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto){
        var order = mapper.toEntity(orderDto);
        service.save(order);
        return ResponseEntity.status(201).body(mapper.toDto(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        Order existingOrder = service.get(id);
        if (existingOrder == null) {
            throw new ResourceNotFoundException("Orden con ID " + id + " no encontrada");
        }
        mapper.updateEntityFromDto(orderDto, existingOrder);
        existingOrder.setId(id);
        service.save(existingOrder);
        return ResponseEntity.ok(mapper.toDto(existingOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> CancelOrder(@PathVariable Long id) {
        try {
            Order existingOrder = service.get(id);
            if (existingOrder == null) {
                return ResponseEntity.status(404).body("Orden con ID " + id + " no encontrada");
            }
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar la orden: " + e.getMessage());
        }
    }
}