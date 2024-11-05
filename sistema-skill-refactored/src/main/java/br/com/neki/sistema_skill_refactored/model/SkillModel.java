package br.com.neki.sistema_skill_refactored.model;

import java.util.UUID;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidImageUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillModel {

	@NotNull(message = "The skill id cannot be null")
	private UUID skillId;

	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	private String description;

	@ValidImageUrl
	@Schema(example = "https://example.com/exemple.png")
	private String image;

}