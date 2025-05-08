package com.example.hacktanton1.dto;

import lombok.Data;

@Data
public class UsuarioLimitsDto {
    private Long userId;
    private Long restrictionId;
    private Long consumedRequests;
    private Long consumedTokens;
    private String windowStart; // ISO string, e.g., 2024-05-07T00:00:00
}