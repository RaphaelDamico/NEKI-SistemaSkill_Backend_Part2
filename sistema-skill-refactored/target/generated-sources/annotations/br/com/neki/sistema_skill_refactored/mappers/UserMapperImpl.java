package br.com.neki.sistema_skill_refactored.mappers;

import br.com.neki.sistema_skill_refactored.domain.Skill;
import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.UserCreateModel;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T10:42:41-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDetailsModel toUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailsModel userDetailsModel = new UserDetailsModel();

        userDetailsModel.setId( user.getId() );
        userDetailsModel.setUserSkills( userSkillListToUserSkillModelList( user.getUserSkills() ) );
        userDetailsModel.setUsername( user.getUsername() );

        return userDetailsModel;
    }

    @Override
    public UserCreateModel toUserCreateModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserCreateModel userCreateModel = new UserCreateModel();

        userCreateModel.setPassword( user.getPassword() );
        userCreateModel.setUsername( user.getUsername() );

        return userCreateModel;
    }

    @Override
    public User toEntity(UserDetailsModel userDetailsModel) {
        if ( userDetailsModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDetailsModel.getId() );
        user.setUserSkills( userSkillModelListToUserSkillList( userDetailsModel.getUserSkills() ) );
        user.setUsername( userDetailsModel.getUsername() );

        return user;
    }

    @Override
    public User toEntity(UserCreateModel userCreateModel) {
        if ( userCreateModel == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userCreateModel.getPassword() );
        user.setUsername( userCreateModel.getUsername() );

        return user;
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

    protected UserSkillModel userSkillToUserSkillModel(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }

        UserSkillModel userSkillModel = new UserSkillModel();

        userSkillModel.setLevel( userSkill.getLevel() );
        userSkillModel.setSkill( skillToSkillModel( userSkill.getSkill() ) );

        return userSkillModel;
    }

    protected List<UserSkillModel> userSkillListToUserSkillModelList(List<UserSkill> list) {
        if ( list == null ) {
            return null;
        }

        List<UserSkillModel> list1 = new ArrayList<UserSkillModel>( list.size() );
        for ( UserSkill userSkill : list ) {
            list1.add( userSkillToUserSkillModel( userSkill ) );
        }

        return list1;
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

    protected UserSkill userSkillModelToUserSkill(UserSkillModel userSkillModel) {
        if ( userSkillModel == null ) {
            return null;
        }

        UserSkill userSkill = new UserSkill();

        userSkill.setLevel( userSkillModel.getLevel() );
        userSkill.setSkill( skillModelToSkill( userSkillModel.getSkill() ) );

        return userSkill;
    }

    protected List<UserSkill> userSkillModelListToUserSkillList(List<UserSkillModel> list) {
        if ( list == null ) {
            return null;
        }

        List<UserSkill> list1 = new ArrayList<UserSkill>( list.size() );
        for ( UserSkillModel userSkillModel : list ) {
            list1.add( userSkillModelToUserSkill( userSkillModel ) );
        }

        return list1;
    }
}
