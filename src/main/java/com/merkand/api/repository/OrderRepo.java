package com.merkand.api.repository;

import com.merkand.api.entity.Order;
import com.merkand.api.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderRepo extends JpaRepository<Order, Long> {
    ArrayList<Order> findByStatus(Status status);
}