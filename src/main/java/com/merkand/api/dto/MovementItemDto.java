package com.merkand.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for MovementItem entity.
 * Uses Java 21 Record for reduced boilerplate and includes validation constraints.
 */
public record MovementItemDto(
        Long id,
        @NotNull @Min(1) int quantity,
        @NotNull Long productId
) {}