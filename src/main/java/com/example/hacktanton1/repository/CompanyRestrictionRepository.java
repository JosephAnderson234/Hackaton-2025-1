package com.example.hacktanton1.repository;

import com.example.hacktanton1.domain.model.CompanyRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRestrictionRepository extends JpaRepository<CompanyRestriction, Long> {
}
