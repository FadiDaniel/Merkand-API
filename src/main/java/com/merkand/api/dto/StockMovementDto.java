package com.merkand.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for StockMovement entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record StockMovementDto(
        Long id,
        @Pattern(regexp = "IN|OUT|ADJUST") String movementType,
        String reference,
        @PastOrPresent LocalDateTime date,
        @NotNull Long userId,
        String userName,
        List<MovementItemDto> items
) {}
