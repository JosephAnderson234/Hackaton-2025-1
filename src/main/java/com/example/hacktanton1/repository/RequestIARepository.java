package com.example.hacktanton1.repository;

import com.example.hacktanton1.domain.model.RequestIA;
import com.example.hacktanton1.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestIARepository extends JpaRepository<RequestIA, Long> {
    List<RequestIA> findByUsuario(Usuario usuario);
}
