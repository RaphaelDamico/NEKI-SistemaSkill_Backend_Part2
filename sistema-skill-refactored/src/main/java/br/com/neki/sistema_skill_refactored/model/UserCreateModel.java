package br.com.neki.sistema_skill_refactored.model;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateModel {
	
    @NotBlank(message = "The username field is required")
	private String username;
	
    @ValidPassword
	private String password;
	
}