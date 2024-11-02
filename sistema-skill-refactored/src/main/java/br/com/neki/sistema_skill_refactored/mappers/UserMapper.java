package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.model.UserCreateModel;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDetailsModel toUserModel(User user);
	UserCreateModel toUserCreateModel(User user);
	User toEntity(UserDetailsModel userDetailsModel);
	User toEntity(UserCreateModel userCreateModel);
}
