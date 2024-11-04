package br.com.neki.sistema_skill_refactored.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.neki.sistema_skill_refactored.domain.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {
	Optional<Skill> findBySkillName(String skillName);

	 @Query("SELECT s FROM Skill s WHERE LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skillNameFilter, '%'))")
	 Page<Skill> findBySkillNameContaining(@Param("skillNameFilter") String skillNameFilter, Pageable pageable);
}