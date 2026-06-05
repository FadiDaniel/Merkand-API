package com.merkand.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for Order entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record OrderDto(
        Long id,
        @NotBlank String orderNumber,
        @PastOrPresent LocalDate orderDate,
        @Positive double totalAmount,
        @Pattern(regexp = "PENDING|RECEIVED|CANCELLED") String status,
        List<OrderItemDto> orderItemList,
        String observations,
        Long supplierId,
        String supplierName,
        Long userId,
        String userName
) {}
