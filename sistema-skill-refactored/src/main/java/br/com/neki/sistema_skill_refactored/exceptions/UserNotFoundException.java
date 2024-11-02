package br.com.neki.sistema_skill_refactored.exceptions;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	public UserNotFoundException(UUID id) {
		this(String.format("No skill found with id %s", id));
	}
	
}
