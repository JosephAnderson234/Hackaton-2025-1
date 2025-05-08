package com.example.hacktanton1.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyResponseDto {
    CompanyDto resCompany;
    JwtAuthenticationResponse JwtAuthenticationResponse;
}
