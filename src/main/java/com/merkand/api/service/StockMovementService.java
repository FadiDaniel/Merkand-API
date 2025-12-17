package com.merkand.api.service;

import com.merkand.api.entity.StockMovement;

import java.util.ArrayList;

public interface StockMovementService extends CurdService<StockMovement> {
    ArrayList<StockMovement> getByProductId(Long productId);

}
