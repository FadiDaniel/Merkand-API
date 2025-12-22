package com.merkand.api.controller;

import com.merkand.api.dto.OrderDto;
import com.merkand.api.entity.Order;
import com.merkand.api.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllOrders(){
        try {
            List<OrderDto> ordersDto = service.getAll()
                    .stream()
                    .map(order -> mapper.map(order, OrderDto.class))
                    .toList();
            System.out.println(ordersDto);
            return ResponseEntity.ok(ordersDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la lista de ordenes: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto){
        try {
            var order = mapper.map(orderDto, Order.class);
            service.save(order);
            var createdOrderDto = mapper.map(order, OrderDto.class);
            return ResponseEntity.status(201).body(createdOrderDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear la orden: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al procesar la orden");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        try {
            Order existingOrder = service.get(id);
            if (existingOrder == null) {
                return ResponseEntity.status(404).body("Orden con ID " + id + " no encontrada");
            }
            mapper.map(orderDto, existingOrder);
            existingOrder.setId(id);
            service.save(existingOrder);
            return ResponseEntity.ok(mapper.map(existingOrder, OrderDto.class));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar la orden: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al actualizar la orden");
        }
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