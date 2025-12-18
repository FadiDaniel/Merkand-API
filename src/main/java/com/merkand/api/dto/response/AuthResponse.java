package com.merkand.api.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String token;
    private  final String role;
    private final String userName;
}

