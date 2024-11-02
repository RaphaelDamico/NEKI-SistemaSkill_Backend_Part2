package br.com.neki.sistema_skill_refactored.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillCreateModel {

	@NotBlank(message = "The name field is required")
	private String skillName;

	@NotBlank(message = "The description field is required")
	@Length(max = 300, message = "The description must be at most 200 characters")
	private String description;
	
	@Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "invalid url")
    @NotBlank(message = "The image field is required")
	private String image;

}