package com.merkand.api.service;

import com.merkand.api.entity.OrderItem;

import java.util.ArrayList;

public interface OrderItemService extends CurdService<OrderItem> {

    ArrayList<OrderItem> getByOrderId(Long orderId);
}
