package com.example.hacktanton1.domain.service;

import com.example.hacktanton1.dto.CompanyRestrictionDto;

import java.util.List;


public interface CompanyRestrictionService {
    CompanyRestrictionDto create(CompanyRestrictionDto dto);
    List<CompanyRestrictionDto> findAll();
    CompanyRestrictionDto update(Long id, CompanyRestrictionDto dto);
    void delete(Long id);
}
