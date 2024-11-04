package br.com.neki.sistema_skill_refactored.mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.input.SkillCreateInput;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T22:00:16-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class SkillMapperImpl implements SkillMapper {

    @Override
    public SkillModel toSkillModel(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillModel skillModel = new SkillModel();

        skillModel.setDescription( skill.getDescription() );
        skillModel.setId( skill.getId() );
        skillModel.setImage( skill.getImage() );
        skillModel.setSkillName( skill.getSkillName() );

        return skillModel;
    }

    @Override
    public SkillCreateInput toSkillCreateInput(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillCreateInput skillCreateInput = new SkillCreateInput();

        skillCreateInput.setDescription( skill.getDescription() );
        skillCreateInput.setImage( skill.getImage() );
        skillCreateInput.setSkillName( skill.getSkillName() );

        return skillCreateInput;
    }

    @Override
    public Skill toEntity(SkillModel skillModel) {
        if ( skillModel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setDescription( skillModel.getDescription() );
        skill.setId( skillModel.getId() );
        skill.setImage( skillModel.getImage() );
        skill.setSkillName( skillModel.getSkillName() );

        return skill;
    }

    @Override
    public Skill toEntity(SkillCreateInput skillCreateModel) {
        if ( skillCreateModel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setDescription( skillCreateModel.getDescription() );
        skill.setImage( skillCreateModel.getImage() );
        skill.setSkillName( skillCreateModel.getSkillName() );

        return skill;
    }
}
