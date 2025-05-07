package com.example.hacktanton1.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class RequestIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private ModelosIA model;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String query;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column(name = "tokens_used", nullable = false)
    private Long tokensUsed;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "image_file_name")
    private String imageFileName;

}
