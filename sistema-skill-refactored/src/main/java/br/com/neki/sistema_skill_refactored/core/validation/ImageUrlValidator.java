package br.com.neki.sistema_skill_refactored.core.validation;

import br.com.neki.sistema_skill_refactored.core.validation.annotation.ValidImageUrl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageUrlValidator implements ConstraintValidator<ValidImageUrl	, String> {
	
	private static final int MAX_SIZE = 300;

    @Override
    public void initialize(ValidImageUrl constraintAnnotation) {
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (url == null || url.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The URL in the image field is required.")
                   .addConstraintViolation();
            return false;
        }
        if (url.length() > MAX_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The image URL must be at most " + MAX_SIZE + " characters.")
                   .addConstraintViolation();
            return false;
        }
        
        boolean matches = url.matches("^(https?|ftp)://[^\s/$.?#].[^\s]*$");
        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Invalid image url"
            ).addConstraintViolation();
            return false;
        }
        
        return true;
    }
}
