package br.com.neki.sistema_skill_refactored.model.input;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateInput {
	
    @NotBlank(message = "The username field is required")
	private String username;
	
    @ValidPassword
	private String password;
	
}