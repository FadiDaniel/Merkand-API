package com.merkand.api.service;

import com.merkand.api.entity.Order;

import java.util.ArrayList;

public interface OrderService {
    Order get(Long orderId);
    ArrayList<Order> getAll();
    void save(Order order);
    void delete(Long orderId);
    ArrayList<Order> getByStatus(com.merkand.api.entity.enums.Status status);
}
