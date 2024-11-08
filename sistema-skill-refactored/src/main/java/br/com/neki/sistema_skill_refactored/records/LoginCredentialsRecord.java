package br.com.neki.sistema_skill_refactored.records;

import jakarta.validation.constraints.NotBlank;

public record LoginCredentialsRecord(
		@NotBlank(message = "The username field is required")
		String username,
		
		@NotBlank(message = "The passw0rd field is required")
		String password
		) {}
