package br.com.neki.sistema_skill_refactored.model;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsModel {
	private UUID userId;
	
    @NotBlank(message = "The username field is required")
	private String username;
	
	private List<UserSkillModel> userSkills;
}