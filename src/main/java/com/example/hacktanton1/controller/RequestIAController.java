package com.example.hacktanton1.controller;



import com.example.hacktanton1.domain.service.RequestIAService;
import com.example.hacktanton1.dto.ModelosIADto;
import com.example.hacktanton1.dto.RequestIARequestDto;
import com.example.hacktanton1.dto.RequestIAResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
public class RequestIAController {

    private final RequestIAService requestIAService;

    @PostMapping("/request/{userId}")
    public ResponseEntity<RequestIAResponseDto> makeRequest(
            @PathVariable Long userId,
            @RequestBody RequestIARequestDto dto
    ) {
        return ResponseEntity.ok(requestIAService.makeRequest(userId, dto));
    }

    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<RequestIAResponseDto>> getUserRequests(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(requestIAService.getAllRequestsByUser(userId));
    }

    @GetMapping("/models")
    public ResponseEntity<List<ModelosIADto>> getAllModels() {
        return ResponseEntity.ok(requestIAService.getAllModels());
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<ModelosIADto> getModelById(@PathVariable Long id) {
        ModelosIADto dto = new ModelosIADto();
        var model = requestIAService.getModelById(id);
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setProvider(model.getProvider());
        dto.setType(model.getType());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/models")
    public ResponseEntity<ModelosIADto> createModel(@RequestBody ModelosIADto dto) {
        return ResponseEntity.ok(requestIAService.createModel(dto));
    }
}