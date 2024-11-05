package br.com.neki.sistema_skill_refactored.mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T21:04:44-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class SkillMapperImpl implements SkillMapper {

    @Override
    public SkillModel toSkillModel(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillModel skillModel = new SkillModel();

        skillModel.setDescription( skill.getDescription() );
        skillModel.setImage( skill.getImage() );
        skillModel.setSkillId( skill.getSkillId() );
        skillModel.setSkillName( skill.getSkillName() );

        return skillModel;
    }

    @Override
    public SkillCreateModel toSkillCreateModel(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillCreateModel skillCreateModel = new SkillCreateModel();

        skillCreateModel.setDescription( skill.getDescription() );
        skillCreateModel.setImage( skill.getImage() );
        skillCreateModel.setSkillName( skill.getSkillName() );

        return skillCreateModel;
    }

    @Override
    public Skill toEntity(SkillModel skillModel) {
        if ( skillModel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setDescription( skillModel.getDescription() );
        skill.setImage( skillModel.getImage() );
        skill.setSkillId( skillModel.getSkillId() );
        skill.setSkillName( skillModel.getSkillName() );

        return skill;
    }

    @Override
    public Skill toEntity(SkillCreateModel skillCreateModel) {
        if ( skillCreateModel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setDescription( skillCreateModel.getDescription() );
        skill.setImage( skillCreateModel.getImage() );
        skill.setSkillName( skillCreateModel.getSkillName() );

        return skill;
    }

    @Override
    public Skill toEntity(SkillCreateAndAssignModel skillCreateAndAssignModel) {
        if ( skillCreateAndAssignModel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setDescription( skillCreateAndAssignModel.getDescription() );
        skill.setImage( skillCreateAndAssignModel.getImage() );
        skill.setSkillName( skillCreateAndAssignModel.getSkillName() );

        return skill;
    }
}
