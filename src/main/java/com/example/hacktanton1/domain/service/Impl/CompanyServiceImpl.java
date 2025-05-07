package com.example.hacktanton1.domain.service.Impl;

import com.example.hack1.domain.model.Company;
import com.example.hack1.domain.service.CompanyService;
import com.example.hack1.dto.CompanyDto;
import com.example.hack1.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepo) {
        this.companyRepository = companyRepo;
    }
    @Override
    public CompanyDto create(CompanyDto dto) {
        Company c = new Company();
        c.setName(dto.getName());
        c.setRuc(dto.getRuc());
        c.setAffiliationDate(dto.getAffiliationDate());
        c.setActive(dto.isActive());

        Company saved = companyRepository.save(c);

        CompanyDto out = new CompanyDto();
        out.setId(saved.getId());
        out.setName(saved.getName());
        out.setRuc(saved.getRuc());
        out.setAffiliationDate(saved.getAffiliationDate());
        out.setActive(saved.isActive());
        return out;
    }
    @Override
    public List<CompanyDto> findALL(){
        return companyRepository.findAll().stream().map(c -> {
            CompanyDto dto = new CompanyDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setRuc(c.getRuc());
            dto.setAffiliationDate(c.getAffiliationDate());
            dto.setActive(c.isActive());
            return dto;
        }).toList();
    }
    @Override
    public CompanyDto findById(Long id) {
        Company c = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        CompanyDto dto = new CompanyDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setRuc(c.getRuc());
        dto.setAffiliationDate(c.getAffiliationDate());
        dto.setActive(c.isActive());
        return dto;
    }
    @Override
    public CompanyDto update(Long id, CompanyDto dto) {
        Company c = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        c.setName(dto.getName());
        c.setRuc(dto.getRuc());
        c.setAffiliationDate(dto.getAffiliationDate());
        c.setActive(dto.isActive());
        Company updated = companyRepository.save(c);

        CompanyDto out = new CompanyDto();
        out.setId(updated.getId());
        out.setName(updated.getName());
        out.setRuc(updated.getRuc());
        out.setAffiliationDate(updated.getAffiliationDate());
        out.setActive(updated.isActive());
        return out;
    }
    @Override
    public void toggleStatus(Long id) {
        Company c = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        c.setActive(!c.isActive());
        companyRepository.save(c);
    }
    @Override
    public Object getConsumptionReport(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        // Simulación de reporte
        return Map.of(
                "companyId", company.getId(),
                "name", company.getName(),
                "totalRequests", 42,
                "tokensUsed", 12400
        );
    }

}
