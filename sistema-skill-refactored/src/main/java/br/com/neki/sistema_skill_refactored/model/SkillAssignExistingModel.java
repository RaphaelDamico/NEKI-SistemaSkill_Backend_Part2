package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillAssignExistingModel {

	@NotNull(message = "The user id cannot be null")
	@NotEmpty(message = "The user id cannot be empty")
	private String userId;

	@NotNull(message = "The skill id cannot be null")
	@NotEmpty(message = "The skill id cannot be empty")
	private String skillId;

}
