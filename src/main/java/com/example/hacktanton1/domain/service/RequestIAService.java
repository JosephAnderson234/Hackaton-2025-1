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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class RequestIAService {

    private final RequestIARepository requestIARepository;
    private final ModelosIARepository modelosIARepository;
    private final UsuarioRepository usuarioRepository;

    String endpoint = "https://models.github.ai/inference";
    @Value("${github-token}")
    String key;

    public RequestIAResponseDto makeRequest(Long userId, RequestIARequestDto dto) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ModelosIA modelo = modelosIARepository.findById(dto.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Modelo IA no encontrado"));
        RequestIA request = new RequestIA();
        request.setUsuario(usuario);
        request.setModel(modelo);
        request.setQuery(dto.getQuery());

        request.setTimestamp(LocalDateTime.now());


        String query = dto.getQuery();
        if (dto.getType().equalsIgnoreCase("multimodal") && dto.getImageBase64() != null) {
            request.setImageFileName("imagen-subida.png");
            query = "Imagen subida en base 64: " + dto.getImageBase64() + ". Consulta: " + dto.getQuery();
        }

        request.setTokensUsed((long) (dto.getQuery().length() * 0.5 + (dto.getImageBase64() != null ? dto.getImageBase64().length() * 0.1 : 0)));
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();



        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(query)
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(modelo.getProvider());

        ChatCompletions completions = client.complete(chatCompletionsOptions);

        String response = completions.getChoice().getMessage().getContent();



        request.setResponse(response);
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

    public ModelosIADto createModel(ModelosIADto model){
        ModelosIA modelo = new ModelosIA();
        modelo.setName(model.getName());
        modelo.setProvider(model.getProvider());
        modelo.setType(model.getType());
        ModelosIA newModel = modelosIARepository.save(modelo);

        ModelosIADto dto = new ModelosIADto();
        dto.setId(newModel.getId());
        dto.setName(newModel.getName());
        dto.setProvider(newModel.getProvider());
        dto.setType(newModel.getType());

        return dto;
    }
}

