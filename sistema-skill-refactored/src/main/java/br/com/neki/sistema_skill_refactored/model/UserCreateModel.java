package br.com.neki.sistema_skill_refactored.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateModel {
	
    @NotBlank(message = "The username field is required")
	private String username;
	
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
    	    message = "The password must contain at least one uppercase letter, one number, one special character, and be between 8 and 30 characters long")
    @NotBlank(message = "The password field is required")
	private String password;
	
}