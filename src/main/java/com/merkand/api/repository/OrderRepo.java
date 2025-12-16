package com.merkand.api.repository;

import com.merkand.api.entity.Order;
import com.merkand.api.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItemList")
    List<Order> findAllWithItems();

    ArrayList<Order> findByStatus(Status status);
    ArrayList<Order> findBySupplierId(Long supplierId);
}