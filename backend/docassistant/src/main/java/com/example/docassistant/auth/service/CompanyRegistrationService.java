package com.example.docassistant.auth.service;

import com.example.docassistant.auth.dto.CompanyRegistrationRequest;
import com.example.docassistant.auth.dto.CompanyRegistrationResponse;

import com.example.docassistant.company.entity.Company;
import com.example.docassistant.company.repository.CompanyRepository;

import com.example.docassistant.user.entity.User;
import com.example.docassistant.user.enums.Role;
import com.example.docassistant.user.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class CompanyRegistrationService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyRegistrationService(
            CompanyRepository companyRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CompanyRegistrationResponse register(
            CompanyRegistrationRequest request
    ) {

        String companyName = request.companyName().trim();
        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        if (companyRepository.existsByNameIgnoreCase(companyName)) {
            throw new IllegalArgumentException(
                    "Company name already registered"
            );
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }

        // 1. Create company
        Company company = new Company(companyName);

        Company savedCompany =
                companyRepository.save(company);

        // 2. Hash administrator password
        String hashedPassword =
                passwordEncoder.encode(request.password());

        // 3. Create company administrator
        User admin = new User(
                savedCompany,
                request.fullName().trim(),
                email,
                hashedPassword,
                Role.COMPANY_ADMIN
        );

        User savedUser = userRepository.save(admin);

        // 4. Return response
        return new CompanyRegistrationResponse(
                savedCompany.getId(),
                savedCompany.getName(),
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                "Company registered successfully"
        );
    }
}