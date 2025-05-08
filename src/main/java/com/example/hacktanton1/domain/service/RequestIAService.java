package com.example.hacktanton1.domain.service;


import com.example.hacktanton1.domain.model.ModelosIA;
import com.example.hacktanton1.domain.model.RequestIA;
import com.example.hacktanton1.domain.model.Usuario;
import com.example.hacktanton1.dto.ModelosIADto;
import com.example.hacktanton1.dto.RequestIARequestDto;
import com.example.hacktanton1.dto.RequestIAResponseDto;
import com.example.hacktanton1.repository.ModelosIARepository;
import com.example.hacktanton1.repository.RequestIARepository;
import com.example.hacktanton1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class RequestIAService {

    private final RequestIARepository requestIARepository;
    private final ModelosIARepository modelosIARepository;
    private final UsuarioRepository usuarioRepository;

    public RequestIAResponseDto makeRequest(Long userId, RequestIARequestDto dto) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ModelosIA modelo = modelosIARepository.findById(dto.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Modelo IA no encontrado"));

        RequestIA request = new RequestIA();
        request.setUsuario(usuario);
        request.setModel(modelo);
        request.setQuery(dto.getQuery());
        request.setTokensUsed((long) (Math.random() * 1000));
        request.setTimestamp(LocalDateTime.now());

        // Simulación de respuesta
        request.setResponse("Respuesta simulada para la consulta: " + dto.getQuery());

        if (dto.getType().equalsIgnoreCase("multimodal") && dto.getImageBase64() != null) {
            request.setImageFileName("imagen-simulada.png");
        }

        requestIARepository.save(request);

        RequestIAResponseDto responseDto = new RequestIAResponseDto();
        responseDto.setModelName(modelo.getName());
        responseDto.setResponse(request.getResponse());
        responseDto.setTokensUsed(request.getTokensUsed());
        responseDto.setTimestamp(request.getTimestamp());
        responseDto.setImageFileName(request.getImageFileName());
        return responseDto;
    }

    public List<RequestIAResponseDto> getAllRequestsByUser(Long userId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return requestIARepository.findByUsuario(usuario).stream().map(req -> {
            RequestIAResponseDto dto = new RequestIAResponseDto();
            dto.setModelName(req.getModel().getName());
            dto.setResponse(req.getResponse());
            dto.setTokensUsed(req.getTokensUsed());
            dto.setTimestamp(req.getTimestamp());
            dto.setImageFileName(req.getImageFileName());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ModelosIADto> getAllModels() {
        return modelosIARepository.findAll().stream().map(model -> {
            ModelosIADto dto = new ModelosIADto();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setProvider(model.getProvider());
            dto.setType(model.getType());
            return dto;
        }).collect(Collectors.toList());
    }

    public ModelosIA getModelById(Long id) {
        return modelosIARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Modelo IA no encontrado"));
    }
}

