package com.example.hacktanton1.controller;



import com.example.hacktanton1.domain.service.UsuarioLimitsService;
import com.example.hacktanton1.dto.UsuarioLimitsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioLimitsController {

    private final UsuarioLimitsService usuarioLimitsService;

    public UsuarioLimitsController(UsuarioLimitsService usuarioLimitService) {
        this.usuarioLimitsService = usuarioLimitService;
    }


    @PostMapping("/{id}/limits")
    public ResponseEntity<UsuarioLimitsDto> assignLimit(@PathVariable Long id, @RequestBody UsuarioLimitsDto dto) {
        return ResponseEntity.ok(usuarioLimitsService.assignLimit(id, dto));
    }

    @GetMapping("/{id}/limits")
    public ResponseEntity<List<UsuarioLimitsDto>> getLimits(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioLimitsService.getUserLimits(id));
    }
}

