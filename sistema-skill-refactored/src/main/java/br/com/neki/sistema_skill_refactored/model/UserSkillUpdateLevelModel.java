package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSkillUpdateLevelModel {
	
	@NotNull(message = "The user Id must not be null")
	private UUID userSkillId;
	
	@NotNull
	@Min(value = 1, message = "The value must be at least {value}.")
	@Max(value = 5, message = "The value must be at most {value}.")
	private Integer level;

}
