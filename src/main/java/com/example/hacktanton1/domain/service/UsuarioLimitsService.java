package com.example.hacktanton1.domain.service;



import com.example.hacktanton1.dto.UsuarioLimitsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioLimitsService {
    UsuarioLimitsDto assignLimit(Long userId, UsuarioLimitsDto dto);
    List<UsuarioLimitsDto> getUserLimits(Long userId);
}
