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

    @Query("SELECT s FROM Skill s WHERE s NOT IN (SELECT us.skill FROM UserSkill us WHERE us.user.userId = :userId)")
    Page<Skill> findAvailableSkillsForUser(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT s FROM Skill s WHERE LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skillNameFilter, '%')) AND s NOT IN (SELECT us.skill FROM UserSkill us WHERE us.user.userId = :userId)")
    Page<Skill> findAvailableSkillsForUserWithFilter(@Param("userId") UUID userId, @Param("skillNameFilter") String skillNameFilter, Pageable pageable);

}
