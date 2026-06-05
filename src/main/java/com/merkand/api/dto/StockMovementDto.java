package com.merkand.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for StockMovement entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record StockMovementDto(
        Long id,
        @NotNull @Min(1) int quantity,
        @Pattern(regexp = "IN|OUT|ADJUST") String movementType,
        String reference,
        @PastOrPresent LocalDateTime date,
        @NotNull Long productId,
        @NotNull Long userId,
        String userName
) {}
