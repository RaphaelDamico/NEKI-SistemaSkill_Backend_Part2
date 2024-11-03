package br.com.neki.sistema_skill_refactored.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.model.input.UserCreateInput;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDetailsModel toUserModel(User user);
	UserCreateInput toUserCreateInput(User user);
	User toEntity(UserDetailsModel userDetailsModel);
	User toEntity(UserCreateInput userCreateInput);
}
