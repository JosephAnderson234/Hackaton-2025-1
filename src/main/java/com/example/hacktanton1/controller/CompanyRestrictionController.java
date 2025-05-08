package com.example.hacktanton1.controller;

import com.example.hacktanton1.domain.service.CompanyRestrictionService;
import com.example.hacktanton1.dto.CompanyRestrictionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class CompanyRestrictionController {

    private final CompanyRestrictionService restrictionService;

    public CompanyRestrictionController(CompanyRestrictionService restrictionService) {
        this.restrictionService = restrictionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<CompanyRestrictionDto> create(@RequestBody CompanyRestrictionDto dto) {
        return ResponseEntity.ok(restrictionService.create(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<List<CompanyRestrictionDto>> list() {
        return ResponseEntity.ok(restrictionService.findAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<CompanyRestrictionDto> update(@PathVariable Long id, @RequestBody CompanyRestrictionDto dto) {
        return ResponseEntity.ok(restrictionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restrictionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
