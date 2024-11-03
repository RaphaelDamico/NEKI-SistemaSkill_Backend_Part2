package br.com.neki.sistema_skill_refactored.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.exceptions.SkillAlreadyAssignedException;
import br.com.neki.sistema_skill_refactored.exceptions.SkillAlreadyExistsException;
import br.com.neki.sistema_skill_refactored.exceptions.SkillNotFoundException;
import br.com.neki.sistema_skill_refactored.exceptions.UserNotFoundException;
import br.com.neki.sistema_skill_refactored.exceptions.UserSkillNotFoundException;
import br.com.neki.sistema_skill_refactored.mappers.SkillMapper;
import br.com.neki.sistema_skill_refactored.mappers.UserSkillMapper;
import br.com.neki.sistema_skill_refactored.model.SkillAssignExistingModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillUpdateLevelModel;
import br.com.neki.sistema_skill_refactored.model.input.SkillCreateInput;
import br.com.neki.sistema_skill_refactored.repositories.SkillRepository;
import br.com.neki.sistema_skill_refactored.repositories.UserRepository;
import br.com.neki.sistema_skill_refactored.repositories.UserSkillRepository;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSkillRepository userSkillRepository;

	public List<SkillModel> findAll(String skillNameFilter) {
		List<Skill> skills;
		if (skillNameFilter == null || skillNameFilter.isEmpty()) {
			skills = skillRepository.findAll();
		} else {
			skills = skillRepository.findByNameContaining(skillNameFilter);
		}
		return skills.stream().map(SkillMapper.INSTANCE::toSkillModel).collect(Collectors.toList());
	}

	public void save(SkillCreateInput skillCreateInput) {
		skillCreateInput.setSkillName(skillCreateInput.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(skillCreateInput.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + skillCreateInput.getSkillName());
		Skill skillSave = new Skill(skillCreateInput);
		skillRepository.save(skillSave);
	}

	public SkillCreateInput saveAndAddToUser(SkillCreateAndAssignModel skillCreateAndAssignModel) {
		skillCreateAndAssignModel.setSkillName(skillCreateAndAssignModel.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(skillCreateAndAssignModel.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + skillCreateAndAssignModel.getSkillName());
		Skill skillSave = new Skill(skillCreateAndAssignModel);
		skillRepository.save(skillSave);
		User user = userRepository.findById(skillCreateAndAssignModel.getUserId())
				.orElseThrow(() -> new SkillNotFoundException(skillCreateAndAssignModel.getUserId()));
		UserSkill userSkill = new UserSkill();
		userSkill.setSkill(skillSave);
		userSkill.setUser(user);
		userSkill.setLevel(skillCreateAndAssignModel.getLevel());
		user.getUserSkills().add(userSkill);
		userSkillRepository.save(userSkill);
		userRepository.save(user);
		return SkillMapper.INSTANCE.toSkillCreateInput(skillSave);
	}

	public List<UserSkill> addExistingSkillToUser(List<SkillAssignExistingModel> listSkillAssignExistingModel) {
		User user = userRepository.findById(listSkillAssignExistingModel.get(0).getUserId())
				.orElseThrow(() -> new UserNotFoundException(listSkillAssignExistingModel.get(0).getUserId()));
		for (SkillAssignExistingModel assignExistingSkillDTO : listSkillAssignExistingModel) {
			Skill skill = skillRepository.findById(assignExistingSkillDTO.getSkillId())
					.orElseThrow(() -> new SkillNotFoundException(assignExistingSkillDTO.getSkillId()));
			boolean alreadyExists = user.getUserSkills().stream()
					.anyMatch(userSkill -> userSkill.getSkill().getId().equals(skill.getId()));
			if (alreadyExists)
				throw new SkillAlreadyAssignedException("User already has this skill: " + skill.getSkillName());
			UserSkill userSkill = new UserSkill();
			userSkill.setSkill(skill);
			userSkill.setUser(user);
			userSkill.setLevel(1);
			user.getUserSkills().add(userSkill);
			userSkillRepository.save(userSkill);
			userRepository.save(user);
		}

		return user.getUserSkills();
	}

	public UserSkillModel updateUserSkillLevel(UserSkillUpdateLevelModel userSkillUpdateLevelModel) {
		UserSkill userSkill = userSkillRepository.findById(userSkillUpdateLevelModel.getUserSkillId())
				.orElseThrow(() -> new UserSkillNotFoundException(userSkillUpdateLevelModel.getUserSkillId()));
		userSkill.setLevel(userSkillUpdateLevelModel.getLevel());
		userSkillRepository.save(userSkill);
		return UserSkillMapper.INSTANCE.toUserSkillModel(userSkill);
	}

	public UserSkillModel deleteUserSkillById(UUID id) {
		UserSkill userSkill = userSkillRepository.findById(id).orElseThrow(() -> new UserSkillNotFoundException(id));
		userSkillRepository.deleteById(id);
		return UserSkillMapper.INSTANCE.toUserSkillModel(userSkill);
	}

}
