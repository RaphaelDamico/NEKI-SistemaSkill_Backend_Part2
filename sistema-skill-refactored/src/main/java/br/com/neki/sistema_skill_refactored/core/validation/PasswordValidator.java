package br.com.neki.sistema_skill_refactored.core.validation;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The password field is required")
                   .addConstraintViolation();
            return false;
        }
        
        boolean matches = password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$");
        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "The password must contain at least one uppercase letter, one number, one special character, and be between 8 and 30 characters long"
            ).addConstraintViolation();
            return false;
        }
        
        return true;
    }
}
