package com.example.docassistant.company.repository;

import com.example.docassistant.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository
        extends JpaRepository<Company, UUID> {

    boolean existsByNameIgnoreCase(String name);
}