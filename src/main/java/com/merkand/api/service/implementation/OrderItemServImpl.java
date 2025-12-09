package com.merkand.api.service.implementation;

import com.merkand.api.entity.OrderItem;
import com.merkand.api.repository.OrderItemRepo;
import com.merkand.api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderItemServImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo repo;

    @Override
    public OrderItem get(Long orderItemId) {
        Optional<OrderItem> opt = repo.findById(orderItemId);
        return opt.orElse(null);
    }

    @Override
    public ArrayList<OrderItem> getAll() {
        return (ArrayList<OrderItem>) repo.findAll();
    }

    @Override
    public void save(OrderItem orderItem) {
        repo.save(orderItem);
    }

    @Override
    public void delete(Long orderItemId) {
        repo.deleteById(orderItemId);
    }

    @Override
    public ArrayList<OrderItem> getByOrderId(Long orderId) {
        return repo.findByOrder_Id(orderId);
    }
}
