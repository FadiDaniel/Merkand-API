package com.merkand.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Data Transfer Object for OrderItem entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record OrderItemDto(
        Long itemId,
        @Min(1) int quantity,
        @Positive double unitPrice,
        double subTotal,

        @NotNull Long productId,
        @NotBlank String productName,
        @NotNull Long orderId,
        String orderNumber
) {}
