package com.example.hacktanton1.dto;

import lombok.Data;

@Data
public class CompanyRestrictionDto {
    private Long id;
    private Long companyId;
    private Long modelId;
    private Long maxRequests;
    private Long maxTokens;
    private Integer windowSeconds;
    private String modelName; // opcional, si deseas mostrarlo
}
