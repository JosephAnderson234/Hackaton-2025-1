package com.example.hacktanton1.domain.service;

import com.example.hacktanton1.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto create(CompanyDto dto);
    List<CompanyDto> findALL();
    CompanyDto findById(Long id);
    CompanyDto update(Long id, CompanyDto dto);
    void toggleStatus(Long id);
    Object getConsumptionReport(Long id);
}
