package com.example.hacktanton1.domain.service.Impl;


import com.example.hacktanton1.domain.model.CompanyRestriction;
import com.example.hacktanton1.domain.model.Usuario;
import com.example.hacktanton1.domain.model.UsuarioLimits;
import com.example.hacktanton1.domain.service.UsuarioLimitsService;
import com.example.hacktanton1.dto.UsuarioLimitsDto;
import com.example.hacktanton1.repository.CompanyRestrictionRepository;
import com.example.hacktanton1.repository.UsuarioLimitsRepository;
import com.example.hacktanton1.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioLimitsServiceImpl implements UsuarioLimitsService {

    private final UsuarioRepository usuarioRepository;
    private final CompanyRestrictionRepository restrictionRepository;
    private final UsuarioLimitsRepository usuarioLimitsRepository;

    public UsuarioLimitsServiceImpl(UsuarioRepository usuarioRepository, CompanyRestrictionRepository restrictionRepository, UsuarioLimitsRepository usuarioLimitsRepository) {
        this.usuarioRepository = usuarioRepository;
        this.restrictionRepository = restrictionRepository;
        this.usuarioLimitsRepository = usuarioLimitsRepository;
    }

    @Override
    public UsuarioLimitsDto assignLimit(Long userId, UsuarioLimitsDto dto) {
        Usuario user = usuarioRepository.findById(userId).orElseThrow();
        CompanyRestriction restriction = restrictionRepository.findById(dto.getRestrictionId()).orElseThrow();

        UsuarioLimits limit = new UsuarioLimits();
        limit.setUsuario(user);
        limit.setRestriction(restriction);
        limit.setConsumedRequests(dto.getConsumedRequests() != null ? dto.getConsumedRequests() : 0L);
        limit.setConsumedTokens(dto.getConsumedTokens() != null ? dto.getConsumedTokens() : 0L);
        limit.setWindowStart(LocalDateTime.parse(dto.getWindowStart()));

        UsuarioLimits saved = usuarioLimitsRepository.save(limit);
        dto.setUserId(saved.getUsuario().getId());
        return dto;
    }

    @Override
    public List<UsuarioLimitsDto> getUserLimits(Long userId) {
        return usuarioLimitsRepository.findAll().stream()
                .filter(ul -> ul.getUsuario().getId().equals(userId))
                .map(ul -> {
                    UsuarioLimitsDto dto = new UsuarioLimitsDto();
                    dto.setUserId(ul.getUsuario().getId());
                    dto.setRestrictionId(ul.getRestriction().getId());
                    dto.setConsumedRequests(ul.getConsumedRequests());
                    dto.setConsumedTokens(ul.getConsumedTokens());
                    dto.setWindowStart(ul.getWindowStart().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
