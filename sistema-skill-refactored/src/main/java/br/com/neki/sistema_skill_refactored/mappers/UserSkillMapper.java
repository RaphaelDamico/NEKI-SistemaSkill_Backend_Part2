package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;

@Mapper
public interface UserSkillMapper {
	UserSkillMapper INSTANCE = Mappers.getMapper(UserSkillMapper.class);

	UserSkillModel toUserSkillModel(UserSkill userSkill);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true)
	UserSkill toUserSkillEntity(UserSkillModel userSkillModel);
}
