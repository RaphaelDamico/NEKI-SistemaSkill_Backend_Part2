package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.input.SkillCreateInput;

@Mapper
public interface SkillMapper {
	SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);
	
	SkillModel toSkillModel(Skill skill);
	SkillCreateInput toSkillCreateInput(Skill skill);
	Skill toEntity(SkillModel skillModel);
	Skill toEntity(SkillCreateInput skillCreateModel);
}