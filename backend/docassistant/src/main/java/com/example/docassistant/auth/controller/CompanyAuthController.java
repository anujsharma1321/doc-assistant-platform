package com.example.docassistant.auth.controller;

import com.example.docassistant.auth.dto.CompanyRegistrationRequest;
import com.example.docassistant.auth.dto.CompanyRegistrationResponse;
import com.example.docassistant.auth.service.CompanyRegistrationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class CompanyAuthController {

    private final CompanyRegistrationService registrationService;

    public CompanyAuthController(
            CompanyRegistrationService registrationService
    ) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register-company")
    public ResponseEntity<CompanyRegistrationResponse> registerCompany(
            @Valid @RequestBody CompanyRegistrationRequest request
    ) {

        CompanyRegistrationResponse response =
                registrationService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}