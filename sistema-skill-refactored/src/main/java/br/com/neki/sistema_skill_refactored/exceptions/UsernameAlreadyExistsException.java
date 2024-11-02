package br.com.neki.sistema_skill_refactored.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}