package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillAssignExistingModel {
	@NotNull(message = "The user Id must not be null")
	private UUID userId;
	@NotNull(message = "The skill id cannot be null")
	private UUID skillId;
}
