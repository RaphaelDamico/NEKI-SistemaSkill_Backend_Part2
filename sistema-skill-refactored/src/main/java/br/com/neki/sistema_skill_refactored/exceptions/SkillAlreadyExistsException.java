package br.com.neki.sistema_skill_refactored.exceptions;

public class SkillAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SkillAlreadyExistsException(String message) {
        super(message);
    }
}