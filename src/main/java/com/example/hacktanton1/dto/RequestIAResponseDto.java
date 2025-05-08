package com.example.hacktanton1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestIAResponseDto {
    private String modelName;
    private String response;
    private Long tokensUsed;
    private LocalDateTime timestamp;
    private String imageFileName;
}