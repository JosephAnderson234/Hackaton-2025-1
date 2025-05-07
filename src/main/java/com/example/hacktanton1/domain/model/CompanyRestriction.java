package com.example.hacktanton1.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data

public class CompanyRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private ModelosIA model;

    @Column(name = "max_requests", nullable = false)
    private Long maxRequests;

    @Column(name = "max_tokens", nullable = false)
    private Long maxTokens;

    @Column(name = "window_seconds", nullable = false)
    private Integer windowSeconds;

    @OneToMany(mappedBy = "restriction", cascade = CascadeType.ALL)
    private List<UsuarioLimits> usuarioLimits;

}
