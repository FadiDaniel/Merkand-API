package com.merkand.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private double totalAmount;
    private String status; // "PENDING", "RECEIVED", "CANCELLED"
    private String supplierName;
    private String supplierId;
    private int orderItemListCount; // ¿enviar la lista completa? evaluar


}
