package com.example.hacktanton1.dto;

import lombok.Data;

@Data
public class RequestIARequestDto {
    private Long modelId;
    private String query;
    private String type; // chat, completion, multimodal
    private String imageBase64; // solo para multimodal
}