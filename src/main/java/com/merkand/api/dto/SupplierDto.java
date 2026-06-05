package com.merkand.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for Supplier entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record SupplierDto(
        Long id,
        @NotBlank String nif,
        @NotBlank String name,
        String contactName,
        String phone,
        @Email String email,
        String address,
        boolean active,
        List<ProductDto> productList
) {}
