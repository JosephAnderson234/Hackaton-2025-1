package com.example.hacktanton1.controller;

import com.example.hacktanton1.domain.service.CompanyService;
import com.example.hacktanton1.dto.CompanyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class AdminCompanyController {
    private final CompanyService companyService;

    public AdminCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @PostMapping
    public ResponseEntity<CompanyDto> createcompany(@RequestBody CompanyDto dto) {
        CompanyDto created = companyService.create(dto);
        return ResponseEntity.ok(created);
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CompanyDto>> listALL() {
        return ResponseEntity.ok(companyService.findALL());
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto dto) {
        return ResponseEntity.ok(companyService.update(id, dto));
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
        companyService.toggleStatus(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')")
    @GetMapping("/{id}/consumption")
    public ResponseEntity<Object> getConsumption(@PathVariable Long id) {
        // Puedes crear un DTO personalizado para el reporte si quieres
        return ResponseEntity.ok(companyService.getConsumptionReport(id));
    }


}
