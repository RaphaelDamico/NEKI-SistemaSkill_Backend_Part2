package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillCreateAndAssignModel {

	@NotNull(message = "The user id cannot be null")
	private UUID userId;

	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	private String description;

	@Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "invalid url")
	@NotBlank(message = "The image url field is required")
	private String image;

	@NotNull(message = "The value cannot be null")
	@Min(value = 1, message = "The value must be at least {value}.")
	@Max(value = 5, message = "The value must be at most {value}.")
	private Integer level;

}