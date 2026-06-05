package com.merkand.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for User entity.
 */
public record UserDto(
        Long id,
        @NotBlank String username,
        String role
) {}