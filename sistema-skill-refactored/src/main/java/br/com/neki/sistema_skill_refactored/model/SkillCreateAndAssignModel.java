package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidImageUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillCreateAndAssignModel {

	@NotNull(message = "The user id must not be null")
	private UUID userId;

	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	@Size(max = 150, message = "The description must be at most 150 characters")
	private String description;

	@ValidImageUrl
	@Schema(example = "https://example.com/exemple.png")
	private String image;

	@NotNull(message = "The value must not be null")
	@Min(value = 1, message = "The value must be at least {value}.")
	@Max(value = 5, message = "The value must be at most {value}.")
	private Integer level;

}