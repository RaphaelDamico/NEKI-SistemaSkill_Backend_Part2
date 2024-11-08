package br.com.neki.sistema_skill_refactored.model;

import br.com.neki.sistema_skill_refactored.core.validation.PasswordGroup;
import br.com.neki.sistema_skill_refactored.core.validation.UsernameGroup;
import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateModel {
	
    @NotBlank(message = "The username field is required", groups = UsernameGroup.class)
	private String username;
	
    @ValidPassword(groups = PasswordGroup.class)
	private String password;
	
}