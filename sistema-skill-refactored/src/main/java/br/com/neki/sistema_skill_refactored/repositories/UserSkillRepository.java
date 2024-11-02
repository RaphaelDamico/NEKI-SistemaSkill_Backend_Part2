package br.com.neki.sistema_skill_refactored.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, UUID> {
	
}