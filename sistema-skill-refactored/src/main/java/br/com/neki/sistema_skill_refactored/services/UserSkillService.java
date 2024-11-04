package br.com.neki.sistema_skill_refactored.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.exceptions.UserSkillNotFoundException;
import br.com.neki.sistema_skill_refactored.mappers.UserSkillMapper;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.model.input.UserSkillUpdateLevelInput;
import br.com.neki.sistema_skill_refactored.repositories.UserSkillRepository;

@Service
public class UserSkillService {
	
	@Autowired
	UserSkillRepository userSkillRepository;
	
	
	public UserSkillModel updateUserSkillLevel(UserSkillUpdateLevelInput userSkillUpdateLevelInput) {
		UserSkill userSkill = userSkillRepository.findById(userSkillUpdateLevelInput.getUserSkillId())
				.orElseThrow(() -> new UserSkillNotFoundException(
						userSkillUpdateLevelInput.getUserSkillId()));
		userSkill.setLevel(userSkillUpdateLevelInput.getLevel());
		userSkillRepository.save(userSkill);
		return UserSkillMapper.INSTANCE.toUserSkillModel(userSkill);
	}

	public void deleteUserSkillById(UUID id) {
	    if (!userSkillRepository.existsById(id)) {
	        throw new UserSkillNotFoundException(id);
	    }
	    userSkillRepository.deleteById(id);
	}

}
