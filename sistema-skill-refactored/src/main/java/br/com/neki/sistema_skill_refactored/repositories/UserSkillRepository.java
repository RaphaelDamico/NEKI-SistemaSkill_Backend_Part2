package br.com.neki.sistema_skill_refactored.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, UUID> {

	 @Query("SELECT us FROM UserSkill us JOIN FETCH us.skill WHERE us.user.userId = :userId")
	    Page<UserSkill> findByUserUserId(@Param("userId") UUID userId, Pageable pageable);

	    @Query("SELECT us FROM UserSkill us JOIN FETCH us.skill WHERE us.user.userId = :userId AND LOWER(us.skill.skillName) LIKE LOWER(CONCAT('%', :skillNameFilter, '%'))")
	    Page<UserSkill> findByUserUserIdAndSkillNameFilter(@Param("userId") UUID userId, @Param("skillNameFilter") String skillNameFilter, Pageable pageable);

}