package com.merkand.api.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    private Long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
    private int minimumStock;
    private boolean active;
    private String unitSale;
    private String unitMeasure;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long supplierId;
    private String supplierName;

}
