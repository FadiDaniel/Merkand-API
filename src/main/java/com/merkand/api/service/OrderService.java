package com.merkand.api.service;

import com.merkand.api.entity.Order;

import java.util.ArrayList;

public interface OrderService extends CurdService<Order> {

    ArrayList<Order> getByStatus(com.merkand.api.entity.enums.Status status);
}
