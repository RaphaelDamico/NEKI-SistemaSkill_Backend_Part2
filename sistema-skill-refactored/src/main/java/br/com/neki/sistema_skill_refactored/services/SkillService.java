package br.com.neki.sistema_skill_refactored.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.exceptions.SkillAlreadyAssignedException;
import br.com.neki.sistema_skill_refactored.exceptions.SkillAlreadyExistsException;
import br.com.neki.sistema_skill_refactored.exceptions.SkillNotFoundException;
import br.com.neki.sistema_skill_refactored.exceptions.UserNotFoundException;
import br.com.neki.sistema_skill_refactored.mappers.SkillMapper;
import br.com.neki.sistema_skill_refactored.mappers.UserSkillMapper;
import br.com.neki.sistema_skill_refactored.model.SkillAssignExistingModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.repositories.SkillRepository;
import br.com.neki.sistema_skill_refactored.repositories.UserRepository;
import br.com.neki.sistema_skill_refactored.repositories.UserSkillRepository;
import jakarta.transaction.Transactional;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSkillRepository userSkillRepository;

	@Autowired
	private SkillMapper skillMapper;

	@Autowired
	UserSkillMapper userSkillMapper;

	public Page<SkillModel> findAll(String skillNameFilter, Pageable pageable) {
		Page<Skill> skills;
		if (skillNameFilter == null || skillNameFilter.isEmpty()) {
			skills = skillRepository.findAll(pageable);
			if (skills.isEmpty())
				throw new SkillNotFoundException("No skill found!");
		} else {
			skills = skillRepository.findBySkillNameContaining(skillNameFilter, pageable);
		}
		return skills.map(skillMapper::toSkillModel);
	}

	public void save(SkillCreateModel skillCreateModel) {
		skillCreateModel.setSkillName(skillCreateModel.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(skillCreateModel.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + skillCreateModel.getSkillName());
		Skill skillSave = skillMapper.toEntity(skillCreateModel);
		skillRepository.save(skillSave);
	}
	
	@Transactional
	public SkillCreateModel saveAndAddToUser(SkillCreateAndAssignModel skillCreateAndAssignModel) {
		skillCreateAndAssignModel.setSkillName(skillCreateAndAssignModel.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(skillCreateAndAssignModel.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + skillCreateAndAssignModel.getSkillName());
		Skill skillSave = skillMapper.toEntity(skillCreateAndAssignModel);
		skillRepository.save(skillSave);
		User user = userRepository.findById(skillCreateAndAssignModel.getUserId())
				.orElseThrow(() -> new UserNotFoundException(skillCreateAndAssignModel.getUserId()));
		assignSkillToUser(user, skillSave, skillCreateAndAssignModel.getLevel());
		return skillMapper.toSkillCreateInput(skillSave);
	}

	@Transactional
	public List<UserSkillModel> addExistingSkillToUser(List<SkillAssignExistingModel> listSkillAssignExistingModel) {
		UUID userId = UUID.fromString(listSkillAssignExistingModel.get(0).getUserId());
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		for (SkillAssignExistingModel assignExistingSkillDTO : listSkillAssignExistingModel) {
			UUID skillId = UUID.fromString(assignExistingSkillDTO.getSkillId());
			Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new SkillNotFoundException(skillId));
			assignSkillToUser(user, skill, 1);
		}
		return user.getUserSkills().stream().map(userSkillMapper::toUserSkillModel).collect(Collectors.toList());
	}

	private void assignSkillToUser(User user, Skill skill, int level) {
		if (user.hasSkill(skill)) {
			throw new SkillAlreadyAssignedException("User already has this skill: " + skill.getSkillName());
		}
		UserSkill userSkill = new UserSkill();
		userSkill.setSkill(skill);
		userSkill.setUser(user);
		userSkill.setLevel(level);
		user.getUserSkills().add(userSkill);
		userSkillRepository.save(userSkill);
		userRepository.save(user);
	}
}
