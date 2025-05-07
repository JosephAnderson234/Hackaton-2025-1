package com.example.hacktanton1.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data

public class UsuarioLimits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id", nullable = false)
    private CompanyRestriction restriction;

    @Column(name = "consumed_requests", nullable = false)
    private Long consumedRequests;

    @Column(name = "consumed_tokens", nullable = false)
    private Long consumedTokens =0L;

    @Column(name = "window_start", nullable = false)
    private LocalDateTime windowStart;

}
