package com.example.hacktanton1.domain.service.Impl;


import com.example.hacktanton1.domain.model.Company;
import com.example.hacktanton1.domain.model.CompanyRestriction;
import com.example.hacktanton1.domain.model.ModelosIA;
import com.example.hacktanton1.domain.service.CompanyRestrictionService;
import com.example.hacktanton1.dto.CompanyRestrictionDto;
import com.example.hacktanton1.repository.CompanyRepository;
import com.example.hacktanton1.repository.CompanyRestrictionRepository;
import com.example.hacktanton1.repository.ModelosIARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyRestrictionServiceImpl implements CompanyRestrictionService {

    private final CompanyRepository companyRepository;
    private final CompanyRestrictionRepository restrictionRepository;
    private final ModelosIARepository modelosIARepository;

    public CompanyRestrictionServiceImpl(CompanyRepository companyRepository,
                                         CompanyRestrictionRepository restrictionRepository,
                                         ModelosIARepository modelosIARepository) {
        this.companyRepository = companyRepository;
        this.restrictionRepository = restrictionRepository;
        this.modelosIARepository = modelosIARepository;
    }

    @Override
    public CompanyRestrictionDto create(CompanyRestrictionDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        ModelosIA model = modelosIARepository.findById(dto.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));

        CompanyRestriction restriction = new CompanyRestriction();
        restriction.setCompany(company);
        restriction.setModel(model);
        restriction.setMaxRequests(dto.getMaxRequests());
        restriction.setMaxTokens(dto.getMaxTokens());
        restriction.setWindowSeconds(dto.getWindowSeconds());

        restriction = restrictionRepository.save(restriction);
        dto.setId(restriction.getId());
        return dto;
    }

    @Override
    public List<CompanyRestrictionDto> findAll() {
        return restrictionRepository.findAll().stream().map(r -> {
            CompanyRestrictionDto dto = new CompanyRestrictionDto();
            dto.setId(r.getId());
            dto.setCompanyId(r.getCompany().getId());
            dto.setModelId(r.getModel().getId());
            dto.setMaxRequests(r.getMaxRequests());
            dto.setMaxTokens(r.getMaxTokens());
            dto.setWindowSeconds(r.getWindowSeconds());
            dto.setModelName(r.getModel().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CompanyRestrictionDto update(Long id, CompanyRestrictionDto dto) {
        CompanyRestriction r = restrictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restriction not found"));

        r.setId(id);
        r.setMaxRequests(dto.getMaxRequests());
        r.setMaxTokens(dto.getMaxTokens());
        r.setWindowSeconds(dto.getWindowSeconds());

        restrictionRepository.save(r);

        return dto;
    }

    @Override
    public void delete(Long id) {
        restrictionRepository.deleteById(id);
    }
}
