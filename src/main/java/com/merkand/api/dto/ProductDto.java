package com.merkand.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Data Transfer Object for Product entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record ProductDto(
        Long id,
        @NotBlank String name,
        String description,
        String category,
        @Positive double price,
        @Min(0) int stock,
        @Min(0) int minimumStock,
        boolean active,
        String unitSale,
        String unitMeasure,
        LocalDate createdAt,
        LocalDate updatedAt,
        Long supplierId,
        String supplierName
) {}