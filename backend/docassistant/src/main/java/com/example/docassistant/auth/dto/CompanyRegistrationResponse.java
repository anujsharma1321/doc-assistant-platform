package com.example.docassistant.auth.dto;

import java.util.UUID;

public record CompanyRegistrationResponse(
        UUID companyId,
        String companyName,
        UUID userId,
        String fullName,
        String email,
        String role,
        String message
) {
}