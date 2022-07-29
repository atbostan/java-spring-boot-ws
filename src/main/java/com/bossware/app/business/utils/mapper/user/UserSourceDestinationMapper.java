package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;
import com.bossware.app.shared.dto.UserDto;
import com.bossware.app.shared.entities.User;

@Mapper(componentModel = "spring")
public abstract class UserSourceDestinationMapper {

	public abstract User dtoToEntity(UserDto dto);

	public abstract UserDto entityToDto(User entity);

}
