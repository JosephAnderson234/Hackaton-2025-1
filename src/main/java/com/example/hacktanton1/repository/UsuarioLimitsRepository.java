package com.example.hacktanton1.repository;

import com.example.hack1.domain.model.UsuarioLimits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLimitsRepository extends JpaRepository<UsuarioLimits, Long> {
}
