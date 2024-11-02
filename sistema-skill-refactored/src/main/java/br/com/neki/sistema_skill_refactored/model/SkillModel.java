package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillModel {

	@NotNull(message = "The skill id cannot be null")
	private UUID id;

	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	private String description;

	@Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "invalid url")
	@NotBlank(message = "The image field is required")
	private String image;

}