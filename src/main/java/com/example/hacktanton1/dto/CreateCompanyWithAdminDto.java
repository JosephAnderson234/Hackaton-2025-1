package com.example.hacktanton1.dto;

import lombok.Data;

@Data
public class CreateCompanyWithAdminDto {
    private CompanyDto company;
    private String adminUsername;
    private String adminEmail;
    private String adminPassword;
}