package br.com.neki.sistema_skill_refactored.exceptions;

import java.util.UUID;

public class UserSkillNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public UserSkillNotFoundException(String msg) {
		super(msg);
	}
	
	public UserSkillNotFoundException(UUID id) {
		this(String.format("No skill association found with id %s", id));
	}
	
}
