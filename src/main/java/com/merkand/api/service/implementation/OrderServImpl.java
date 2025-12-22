package com.merkand.api.service.implementation;

import com.merkand.api.entity.Order;
import com.merkand.api.entity.enums.Status;
import com.merkand.api.repository.OrderRepo;
import com.merkand.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServImpl implements OrderService {
    @Autowired
    private OrderRepo repo;

    @Override
    public Order get(Long orderId) {
        Optional<Order> opt = repo.findById(orderId);
        return opt.orElse(null);
    }

    @Override
    public ArrayList<Order> getAll() {
        return (ArrayList<Order>) repo.findAll();
    }

    @Override
    public void save(Order order) {
        repo.save(order);
    }

    @Override
    public void delete(Long orderId) {
        repo.softDeleteById(orderId);
    }

    @Override
    public ArrayList<Order> getByStatus(Status status) {
        return repo.findByStatus(status);
    }
}
