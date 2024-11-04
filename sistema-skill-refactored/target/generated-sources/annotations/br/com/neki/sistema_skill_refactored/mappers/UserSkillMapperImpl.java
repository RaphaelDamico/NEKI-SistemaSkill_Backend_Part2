package br.com.neki.sistema_skill_refactored.mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T21:59:43-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class UserSkillMapperImpl implements UserSkillMapper {

    @Override
    public UserSkillModel toUserSkillModel(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }

        UserSkillModel userSkillModel = new UserSkillModel();

        userSkillModel.setLevel( userSkill.getLevel() );
        userSkillModel.setSkill( skillToSkillModel( userSkill.getSkill() ) );

        return userSkillModel;
    }

    @Override
    public UserSkill toUserSkillEntity(UserSkillModel userSkillModel) {
        if ( userSkillModel == null ) {
            return null;
        }

        UserSkill userSkill = new UserSkill();

        userSkill.setLevel( userSkillModel.getLevel() );
        userSkill.setSkill( skillModelToSkill( userSkillModel.getSkill() ) );

        return userSkill;
    }

    protected SkillModel skillToSkillModel(Skill skill) {
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

    protected Skill skillModelToSkill(SkillModel skillModel) {
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
}
