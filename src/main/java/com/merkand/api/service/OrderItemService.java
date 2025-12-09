package com.merkand.api.service;

import com.merkand.api.entity.OrderItem;

import java.util.ArrayList;

public interface OrderItemService {
    OrderItem get(Long orderItemId);
    ArrayList<OrderItem> getAll();
    void save(OrderItem orderItem);
    void delete(Long orderItemId);
    ArrayList<OrderItem> getByOrderId(Long orderId);
}
