package br.com.neki.sistema_skill_refactored.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.exceptions.UserSkillNotFoundException;
import br.com.neki.sistema_skill_refactored.mappers.UserSkillMapper;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillUpdateLevelModel;
import br.com.neki.sistema_skill_refactored.repositories.UserSkillRepository;

@Service
public class UserSkillService {
	
	@Autowired
	UserSkillRepository userSkillRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSkillMapper userSkillMapper;
	
	 public Page<UserSkillModel> findAll(String skillNameFilter, Pageable pageable) {
	        UUID userId = userService.getAuthenticatedUserId();
	        Page<UserSkill> userSkills;
	        if (skillNameFilter == null || skillNameFilter.isEmpty()) {
	            userSkills = userSkillRepository.findByUserUserId(userId, pageable);
	        } else {
	            userSkills = userSkillRepository.findByUserUserIdAndSkillNameFilter(userId, skillNameFilter, pageable);
	        }
	        return userSkills.map(userSkillMapper::toUserSkillModel);
	    }
	
	
	public UserSkillModel updateUserSkillLevel(UserSkillUpdateLevelModel userSkillUpdateLevelModel) {
		UserSkill userSkill = userSkillRepository.findById(userSkillUpdateLevelModel.getUserSkillId())
				.orElseThrow(() -> new UserSkillNotFoundException(
						userSkillUpdateLevelModel.getUserSkillId()));
		userSkill.setLevel(userSkillUpdateLevelModel.getLevel());
		userSkillRepository.save(userSkill);
		return userSkillMapper.toUserSkillModel(userSkill);
	}

	public void deleteUserSkillById(UUID id) {
	    if (!userSkillRepository.existsById(id)) {
	        throw new UserSkillNotFoundException(id);
	    }
	    userSkillRepository.deleteById(id);
	}

}
