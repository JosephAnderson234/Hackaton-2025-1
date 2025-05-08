package com.example.hacktanton1.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String username;
    private String completeName;
    private String email;
    private String password;
    private Long companyId;
}