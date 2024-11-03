package br.com.neki.sistema_skill_refactored.records;

import java.util.UUID;

public record LoginCredentialsRecord(
	    UUID id,
	    String username,
	    String password
	) {}
