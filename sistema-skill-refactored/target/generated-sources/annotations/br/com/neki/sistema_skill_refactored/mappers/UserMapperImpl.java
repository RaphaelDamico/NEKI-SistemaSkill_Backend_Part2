package br.com.neki.sistema_skill_refactored.mappers;

import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.model.input.UserCreateInput;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T15:55:24-0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDetailsModel toUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailsModel userDetailsModel = new UserDetailsModel();

        userDetailsModel.setId( user.getId() );
        List<UserSkill> list = user.getUserSkills();
        if ( list != null ) {
            userDetailsModel.setUserSkills( new ArrayList<UserSkill>( list ) );
        }
        userDetailsModel.setUsername( user.getUsername() );

        return userDetailsModel;
    }

    @Override
    public UserCreateInput toUserCreateInput(User user) {
        if ( user == null ) {
            return null;
        }

        UserCreateInput userCreateInput = new UserCreateInput();

        userCreateInput.setPassword( user.getPassword() );
        userCreateInput.setUsername( user.getUsername() );

        return userCreateInput;
    }

    @Override
    public User toEntity(UserDetailsModel userDetailsModel) {
        if ( userDetailsModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDetailsModel.getId() );
        List<UserSkill> list = userDetailsModel.getUserSkills();
        if ( list != null ) {
            user.setUserSkills( new ArrayList<UserSkill>( list ) );
        }
        user.setUsername( userDetailsModel.getUsername() );

        return user;
    }

    @Override
    public User toEntity(UserCreateInput userCreateInput) {
        if ( userCreateInput == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userCreateInput.getPassword() );
        user.setUsername( userCreateInput.getUsername() );

        return user;
    }
}
