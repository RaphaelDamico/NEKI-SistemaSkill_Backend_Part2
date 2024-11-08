package br.com.neki.sistema_skill_refactored.model;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidImageUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillCreateModel {
	
	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	@Size(max = 150, message = "The description must be at most 150 characters")
	private String description;
	
	@ValidImageUrl
	@Schema(example = "https://example.com/exemple.png")
	private String image;

}