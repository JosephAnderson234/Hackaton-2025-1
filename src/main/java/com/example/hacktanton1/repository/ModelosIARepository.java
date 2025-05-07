package com.example.hacktanton1.repository;

import com.example.hacktanton1.domain.model.ModelosIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelosIARepository extends JpaRepository<ModelosIA, Long> {
}
