package com.example.hacktanton1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String ruc;
    private LocalDate affiliationDate;
    private boolean active;
}
