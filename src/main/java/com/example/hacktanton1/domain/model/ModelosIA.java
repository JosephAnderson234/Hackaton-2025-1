package com.example.hacktanton1.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class ModelosIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String type; // chat, completion, multimodal

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<CompanyRestriction> companyRestrictions;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<RequestIA> requestsAI;

    // getters and setter
}
