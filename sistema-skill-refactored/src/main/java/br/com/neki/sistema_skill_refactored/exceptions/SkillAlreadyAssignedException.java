package br.com.neki.sistema_skill_refactored.exceptions;

public class SkillAlreadyAssignedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SkillAlreadyAssignedException(String message) {
        super(message);
    }
}