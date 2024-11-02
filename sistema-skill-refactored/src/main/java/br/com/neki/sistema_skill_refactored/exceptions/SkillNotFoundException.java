package br.com.neki.sistema_skill_refactored.exceptions;

import java.util.UUID;

public class SkillNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public SkillNotFoundException(String msg) {
		super(msg);
	}
	
	public SkillNotFoundException(UUID id) {
		this(String.format("No skill found with id %s", id));
	}
	
}
