package com.merkand.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private double totalAmount;
    private String status; // "PENDING", "RECEIVED", "CANCELLED"
    private List<OrderItemDto> orderItemList;
    private String observations;
    private Long supplierId;
    private String supplierName;
    private Long userId;
    private String userName;

}
