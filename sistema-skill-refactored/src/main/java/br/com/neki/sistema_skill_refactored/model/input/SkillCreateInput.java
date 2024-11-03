package br.com.neki.sistema_skill_refactored.model.input;

import org.hibernate.validator.constraints.Length;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidImageUrl;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillCreateInput {
	
	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	@Length(max = 300, message = "The description must be at most 200 characters")
	private String description;
	
	@ValidImageUrl
	private String image;

}