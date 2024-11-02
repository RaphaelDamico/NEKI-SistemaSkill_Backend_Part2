package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.model.SkillCreateModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;

@Mapper
public interface SkillMapper {
	SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);
	
	SkillModel toSkillModel(Skill skill);
	SkillCreateModel toSkillCreateModel(Skill skill);
	Skill toEntity(SkillModel skillModel);
	Skill toEntity(SkillCreateModel skillCreateModel);
}