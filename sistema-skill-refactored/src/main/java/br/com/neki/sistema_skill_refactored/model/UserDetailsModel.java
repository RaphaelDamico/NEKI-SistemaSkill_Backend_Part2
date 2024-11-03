package br.com.neki.sistema_skill_refactored.model;

import java.util.List;
import java.util.UUID;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsModel {
	private UUID id;
	
    @NotBlank(message = "The username is required")
	private String username;
	
	private List<UserSkill> userSkills;
}