package com.merkand.api.dto;

import com.merkand.api.entity.Order;
import com.merkand.api.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long itemId;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    private Long productId;
    private String productName;
    private Long orderId;
    private String orderNumber;


}
