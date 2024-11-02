package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;

@Mapper
public interface UserSkillMapper {
	UserSkillMapper INSTANCE = Mappers.getMapper(UserSkillMapper.class);
	
	UserSkillModel toUserSkillModel(UserSkill userSkill);
	UserSkill toUserSkillEntity(UserSkillModel UserSkillModel);
}
